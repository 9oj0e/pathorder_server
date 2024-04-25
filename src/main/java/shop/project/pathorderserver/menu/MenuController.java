package shop.project.pathorderserver.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import shop.project.pathorderserver._core.utils.ApiUtil;

@RequiredArgsConstructor
@RestController
public class MenuController {
    private final MenuService menuService;

    public ResponseEntity<?> add() { // 메뉴 추가
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    public ResponseEntity<?> update() { // 메뉴 수정
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}