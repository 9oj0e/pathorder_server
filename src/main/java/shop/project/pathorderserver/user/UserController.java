package shop.project.pathorderserver.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    @PostMapping("/join") // 회원가입
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        UserResponse.JoinDTO respDTO = userService.createUser(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/login") // 로그인 TODO: 비밀번호 암호화 하기
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        UserResponse.LoginDTO respDTO = userService.getUser(reqDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer" + respDTO.getJwt()).body(new ApiUtil<>(respDTO.getUser()));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() { // 로그아웃
        session.invalidate();

        return ResponseEntity.ok(new ApiUtil<>("로그아웃 완료"));
    }

    @GetMapping("/api/users/{userId}") // 회원정보 조회
    public ResponseEntity<?> profile(@PathVariable int userId) {
        UserResponse.UserDTO respDTO = userService.getUser(userId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/api/users/{userId}") // 회원정보 수정
    public ResponseEntity<?> update(@RequestBody UserRequest.UpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser newSessionUser = userService.setUser(reqDTO, sessionUser.getId());
        session.setAttribute("sessionUser", newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }

    @PostMapping("/api/users/{userId}") // 사진 등록
    public ResponseEntity<?> uploadImg(@PathVariable int userId, @RequestBody UserRequest.ImgDTO reqDTO) throws IOException {
        UserResponse.ImgDTO respDTO = userService.setImg(reqDTO, userId); // TODO: userId -> sessionUserId

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/api/users/{userId}/orders") // 주문하기
    public ResponseEntity<?> order(@RequestBody UserRequest.OrderDTO reqDTO) {
        UserResponse.OrderDTO respDTO = userService.createOrder(reqDTO);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/users/{userId}/orders") // 회원 주문내역 목록보기
    private ResponseEntity<?> orderList(@PathVariable int userId) {
        UserResponse.OrderListDTO respDTO = userService.getOrderList(userId);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/users/{userId}/orders/{orderId}") // 회원 주문내역 상세보기
    private ResponseEntity<?> orderDetail(@PathVariable int orderId) {
        UserResponse.OrderDetailDTO respDTO = userService.getOrderDetail(orderId);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
