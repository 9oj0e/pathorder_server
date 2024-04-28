package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import shop.project.pathorderserver._core.utils.ApiUtil;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    // 주문내역 목록보기
    @GetMapping("/api/users/{userId}/orders")
    private ResponseEntity<?> orderList(@PathVariable int userId) {
        OrderResponse.OrderListDTO respDTO = orderService.getOrderList(userId);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 주문내역 상세보기
    @GetMapping("/api/users/{userId}/orders/{orderId}")
    private ResponseEntity<?> orderDetail(@PathVariable int orderId) {
        OrderResponse.DetailDTO respDTO = orderService.getOrderDetail(orderId);
        // TODO: 주문 목록보기 (메뉴, 옵션)

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
