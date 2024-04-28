package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;
import shop.project.pathorderserver.menu.MenuResponse;
import shop.project.pathorderserver.menu.MenuService;

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

    // 주문내역
    @GetMapping("/api/users/{userId}/orders")
    private ResponseEntity<?> orderList(@PathVariable int userId) {
        OrderResponse.OrderListDTO respDTO = orderService.getOrderList(userId);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 주문 상세보기
    @GetMapping("/api/users/{userId}/orders/{orderId}")
    private ResponseEntity<?> orderDetail(@PathVariable int orderId) {
        OrderResponse.DetailDTO respDTO = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PostMapping("/api/orders")
    public ResponseEntity<?> order(@RequestBody OrderRequest.SaveDTO reqDTO) {
        OrderResponse.SaveDTO respDTO = orderService.createOrder(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
