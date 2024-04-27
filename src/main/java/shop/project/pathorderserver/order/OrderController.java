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

    // 메뉴별 옵션
    // menuId가 storeId를 가지고 있는데 storeId가 필요한가?
    @GetMapping("/api/stores/{storeId}/menus/{menuId}/options")
    private ResponseEntity<?> option(@PathVariable int menuId) {
        List<MenuResponse.OptionDTO> optionList = menuService.getOption(menuId);

        return ResponseEntity.ok(new ApiUtil(optionList));
    }
}
