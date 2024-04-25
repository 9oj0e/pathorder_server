package shop.project.pathorderserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.project.pathorderserver._core.utils.ApiUtil;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO requestDTO) { // 회원가입

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() { // 로그인

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() { // 로그아웃

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
