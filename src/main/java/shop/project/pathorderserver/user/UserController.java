package shop.project.pathorderserver.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.errors.AppRequest;
import shop.project.pathorderserver._core.utils.ApiUtil;
import shop.project.pathorderserver.store.StoreSseService;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final HttpSession session;
    private final UserService userService;
    private final StoreSseService storeSseService;

    @AppRequest
    @PostMapping("/join") // 회원가입
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO reqDTO, Errors errors) {
        UserResponse.JoinDTO respDTO = userService.createUser(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @AppRequest
    @PostMapping("/login") // 로그인 TODO: 비밀번호 암호화 하기
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO reqDTO, Errors errors) {
        UserResponse.LoginDTO respDTO = userService.getUser(reqDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer " + respDTO.getJwt()).body(new ApiUtil<>(respDTO.getUser()));
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

    @AppRequest
    @PutMapping("/api/users/{userId}") // 회원정보 수정
    public ResponseEntity<?> update(@PathVariable String userId, @RequestBody @Valid UserRequest.UpdateDTO reqDTO, Errors errors) {
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

    @AppRequest
    @PostMapping("/api/users/{userId}/orders") // 주문하기
    public ResponseEntity<?> order(@PathVariable String userId, @RequestBody @Valid UserRequest.OrderDTO reqDTO, Errors errors) {
        UserResponse.OrderDTO respDTO = userService.createOrder(reqDTO);
        storeSseService.createOrderNotification(respDTO.getId(), respDTO.getStoreId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/users/{userId}/orders") // 회원 주문내역 목록보기
    public ResponseEntity<?> orderList(@PathVariable int userId) {
        UserResponse.OrderListDTO respDTO = userService.getOrderList(userId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/users/{userId}/orders/{orderId}") // 회원 주문내역 상세보기
    public ResponseEntity<?> orderDetail(@PathVariable String userId, @PathVariable int orderId) {
        UserResponse.OrderDetailDTO respDTO = userService.getOrderDetail(orderId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
