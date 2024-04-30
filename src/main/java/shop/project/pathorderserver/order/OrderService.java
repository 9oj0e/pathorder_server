package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;
import shop.project.pathorderserver.user.User;
import shop.project.pathorderserver.user.UserRepository;
import shop.project.pathorderserver.user.UserResponse;

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

    @Transactional // 주문하기
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

    // 주문내역 목록보기 (손님)
    public UserResponse.OrderListDTO getOrderList(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));

        return new UserResponse.OrderListDTO(orders);
    }

    @Transactional // 주문내역 상세보기 (손님)
    public UserResponse.OrderDetailDTO getOrderDetail(int orderId) {
        Order order // 단일 주문 내역 조회
                = orderRepository.findById(orderId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));
        List<OrderMenu> orderMenus // 주문 내역의 메뉴 목록.
                = orderMenuRepository.findAllByOrderId(orderId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));
        /* 양방향 매핑으로 변경.
        List<Integer> orderMenuIdList; // 34, 35, 36
        orderMenuIdList // 주문 메뉴별 Id 추출
                = orderMenus.stream().map(orderMenu -> orderMenu.getId()).toList();
        for (int orderMenuId : orderMenuIdList) {
            List<OrderOption> orderOptions // 주문 메뉴별 옵션 목록 조회
                    = orderMenuRepository.findAllOptionByMenuId(orderMenuId)
                            .orElse(null); // 없으면 null
            orderMenus.get(orderMenuId).setOrderOption(orderOptions);
        }
        */
        return new UserResponse.OrderDetailDTO(order, orderMenus);
    }
    // TODO: 주문 수정 (매장 측: 주문 상태 변경)
}
