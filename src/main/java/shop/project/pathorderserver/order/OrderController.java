package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import shop.project.pathorderserver._core.utils.ApiUtil;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuResponse;
import shop.project.pathorderserver.menu.MenuService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;
    private final MenuService menuService;

    // 매장별 메뉴 리스트
    @GetMapping("/api/stores/{storeId}/menus")
    private ResponseEntity<?> menuList(@PathVariable int storeId) {
        List<MenuResponse.StoreMenuDTO> menuList = menuService.getAllStoreMenu(storeId);

        return ResponseEntity.ok(new ApiUtil(menuList));
    }
}
