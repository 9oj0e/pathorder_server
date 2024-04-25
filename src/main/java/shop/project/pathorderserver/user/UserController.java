package shop.project.pathorderserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import shop.project.pathorderserver._core.utils.ApiUtil;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    public ResponseEntity<?> join() { // 회원가입
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    public ResponseEntity<?> login() { // 로그인
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    public ResponseEntity<?> logout() { // 로그아웃
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
