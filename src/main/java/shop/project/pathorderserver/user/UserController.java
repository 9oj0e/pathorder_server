package shop.project.pathorderserver.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;

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

    @PostMapping("/login") // 로그인 TODO: 암호화 하기
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        UserResponse.LoginDTO respDTO = userService.getUser(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/users/{userId}") // 회원정보 조회
    public ResponseEntity<?> profile(@PathVariable int userId) {
        UserResponse.UserDTO respDTO = userService.getUser(userId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() { // 로그아웃
        session.invalidate();

        return ResponseEntity.ok(new ApiUtil<>("로그아웃 완료"));
    }

    @PutMapping("/api/user/{userId}") // 회원정보 수정
    public ResponseEntity<?> update(@RequestBody UserRequest.UpdateDTO reqDTO) {
        UserResponse.UpdateDTO respDTO = userService.setUser(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
