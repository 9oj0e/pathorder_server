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
import shop.project.pathorderserver.store.StoreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;
    private final MenuService menuService;

    // 매장 메뉴
    @GetMapping("/api/stores/{storeId}/menus")
    private ResponseEntity<?> menuList(@PathVariable int storeId) {
        MenuResponse.StoreMenuDTO respDTO = menuService.getStoreNameAndStoreMenu(storeId);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 메뉴별 옵션
    @GetMapping("/api/stores/{storeId}/menus/{menuId}/options")
    private ResponseEntity<?> option(@PathVariable int storeId, @PathVariable int menuId) {
        OrderResponse.MenuOptionDTO respDTO = orderService.getStoreNameAndMenuAndMenuOption(storeId, menuId);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
