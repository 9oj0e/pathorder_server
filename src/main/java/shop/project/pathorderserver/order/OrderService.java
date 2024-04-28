package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver.menu.MenuRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final OrderOptionRepository orderOptionRepository;

    @Transactional
    public OrderResponse.SaveDTO createOrder(OrderRequest.SaveDTO reqDTO) {
        Order order = new Order(reqDTO);

        // 주문 저장
        Order savedOrder = orderRepository.save(order);

        // orderId
        int orderId = order.getId();

        // 메뉴 리스트
        List<OrderMenu> orderMenuList = reqDTO.getOrderMenuList().stream()
                .map(menu -> new OrderMenu(menu, savedOrder))
                .collect(Collectors.toList());
        // 옵션 리스트
        List<OrderOption> orderOptionList = reqDTO.getOrderOptionList() != null ?
                reqDTO.getOrderOptionList().stream()
                        .map(option -> new OrderOption(option, savedOrder))
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        // 주문 메뉴 저장
        orderMenuList.forEach(orderMenu -> orderMenuRepository.save(orderMenu));

        // 주문 옵션 저장
        orderOptionList.forEach(orderOption -> orderOptionRepository.save(orderOption));

        return new OrderResponse.SaveDTO(savedOrder, orderMenuList, orderOptionList);
    }
}
