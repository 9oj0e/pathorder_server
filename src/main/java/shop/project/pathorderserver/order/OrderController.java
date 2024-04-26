package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.project.pathorderserver._core.utils.ApiUtil;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    // 메뉴리스트
//    @GetMapping("/api/stores/{storeId}/order/menus")
//    private ResponseEntity<?> menuList(HttpRequest request) {
//        List<OrderResponse.StoreMenuDTO> respDTO = OrderService.getAllMenu();
//
//        return ResponseEntity.ok(new ApiUtil());
//    }
}
