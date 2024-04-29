package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final OrderMenuOptionRepository orderMenuOptionRepository;
    @Transactional
    public OrderResponse.OrderDTO createOrder(OrderRequest.OrderDTO reqDTO) {
        /*
        Order order = orderRepository.save(new Order(reqDTO)); // 주문 생성
        List<OrderRequest.OrderDTO.OrderMenuDTO> orderMenuList = reqDTO.getOrderMenuList();
        for (int i = 0; i < orderMenuList.size(); i++) {
            int orderMenuId = orderMenuRepository.save(new OrderMenu(orderMenuList.get(i), order)).getId();
            for (int j = 0; j < orderMenuList.get(i).getOrderMenuOptionList().size(); j++) {
                orderMenuOptionRepository.save(new OrderMenuOption(orderMenuList.get(i).getOrderMenuOptionList().get(j), order, orderMenuId));
            }
        }
        */
        return null; // TODO: 응답 결과 return
    }
    // TODO: 주문 수정 (매장 측: 주문 상태 변경)
}
