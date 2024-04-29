package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.project.pathorderserver._core.utils.ApiUtil;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<?> order(@RequestBody OrderRequest.OrderDTO reqDTO) {
        OrderResponse.OrderDTO respDTO = orderService.createOrder(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
    // TODO: 주문 수정 (매장 측: 주문 상태 변경)
}
