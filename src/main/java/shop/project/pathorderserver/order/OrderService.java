package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    // TODO: 주문 등록
    // TODO: 주문 수정 (매장 측: 주문 상태 변경)
}
