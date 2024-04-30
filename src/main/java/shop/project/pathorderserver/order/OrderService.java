package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;
import shop.project.pathorderserver.user.User;
import shop.project.pathorderserver.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final OrderMenuOptionRepository orderMenuOptionRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public OrderResponse.OrderDTO createOrder(OrderRequest.OrderDTO reqDTO) {
        User customer // 유저 번호로 유저 조회
                = userRepository.findById(reqDTO.getCustomerId())
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        Store store // 매장 번호로 업주 조회
                = storeRepository.findById(reqDTO.getStoreId())
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장 번호입니다."));
        Order order // 주문 생성 TODO: status 기본 값 'null'
                = new Order(reqDTO, customer, store);

        List<OrderRequest.OrderDTO.OrderMenuDTO> orderMenuList // 재사용 편의를 위해 'orderMenuList'라 정의
                = reqDTO.getOrderMenuList();

        List<OrderMenu> orderMenus // 응답할 주문 메뉴 리스트 생성
                = new ArrayList<>();
        List<OrderMenuOption> orderMenuOptions // 응답할 주문 메뉴 옵션 리스트 생성
                = new ArrayList<>();
        for (int i = 0; i < orderMenuList.size(); i++) {
            OrderMenu orderMenu
                    = orderMenuRepository.save(new OrderMenu(orderMenuList.get(i), order));
            order.updateTotalPrice(orderMenuList.get(i).getPrice()); // 메뉴별 금액
            for (int j = 0; j < orderMenuList.get(i).getOrderMenuOptionList().size(); j++) {
                OrderMenuOption orderMenuOption
                        = orderMenuOptionRepository.save(new OrderMenuOption(orderMenuList.get(i).getOrderMenuOptionList().get(j), order, orderMenu));
                order.updateTotalPrice(orderMenuList.get(i).getOrderMenuOptionList().get(j).getPrice()); // 옵션별 금액
                orderMenuOptions.add(orderMenuOption); // 추가
            }
            orderMenus.add(orderMenu); // 추가
        }
        orderRepository.save(order); // DB insert

        return new OrderResponse.OrderDTO(order, orderMenus, orderMenuOptions);
    }
    // TODO: 주문 수정 (매장 측: 주문 상태 변경)
}
