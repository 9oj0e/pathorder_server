package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;
import shop.project.pathorderserver.menu.MenuResponse;
import shop.project.pathorderserver.menu.MenuService;

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
    // menuId가 storeId를 가지고 있는데 storeId가 필요한가?
    @GetMapping("/api/stores/{storeId}/menus/{menuId}/options")
    private ResponseEntity<?> option(@PathVariable int menuId) {
        List<MenuResponse.OptionDTO> optionList = menuService.getOption(menuId);

        return ResponseEntity.ok(new ApiUtil(optionList));
    }

    @PostMapping("/orders")
    public ResponseEntity<?> order(@RequestBody OrderRequest.SaveDTO reqDTO) {
        OrderResponse.SaveDTO respDTO = orderService.createOrder(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
