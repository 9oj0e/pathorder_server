package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    // 주문내역 목록보기
    public OrderResponse.OrderListDTO getOrderList(int userId) {
        List<Order> orderList = orderRepository.findByUserId(userId).get();
        return new OrderResponse.OrderListDTO(orderList);
    }

    // 주문 상세보기
    @Transactional
    public OrderResponse.DetailDTO getOrderDetail(int orderId) {
        Order order = orderRepository.findById(orderId).get();
        return new OrderResponse.DetailDTO(order);
    }
}
