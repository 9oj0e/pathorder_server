package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;
import shop.project.pathorderserver.user.User;
import shop.project.pathorderserver.user.UserRepository;
import shop.project.pathorderserver.user.UserRequest;
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
    public UserResponse.OrderDTO createOrder(UserRequest.OrderDTO reqDTO) {
        User customer // 유저 번호로 유저 조회
                = userRepository.findById(reqDTO.getCustomerId())
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        Store store // 매장 번호로 업주 조회
                = storeRepository.findById(reqDTO.getStoreId())
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장 번호입니다."));
        Order order // 주문 생성 TODO: status 기본 값 'null'
                = new Order(reqDTO, customer, store);

        List<UserRequest.OrderDTO.OrderMenuDTO> orderMenuList // 재사용 편의를 위해 'orderMenuList'라 정의
                = reqDTO.getOrderMenuList();

        List<OrderMenu> orderMenus // 응답할 주문 메뉴 리스트 생성
                = new ArrayList<>();
        List<OrderMenuOption> orderMenuOptions // 응답할 주문 메뉴 옵션 리스트 생성
                = new ArrayList<>();
        for (int om = 0; om < orderMenuList.size(); om++) {
            OrderMenu orderMenu // 주문 메뉴 엔티티 생성 및 INSERT
                    = orderMenuRepository.save(new OrderMenu(orderMenuList.get(om), order));
            for (int omo = 0; omo < orderMenuList.get(om).getOrderMenuOptionList().size(); omo++) {
                OrderMenuOption orderMenuOption // 주문 메뉴 옵션 엔티티 생성 및 INSERT
                        = orderMenuOptionRepository.save(new OrderMenuOption(orderMenuList.get(om).getOrderMenuOptionList().get(omo), order, orderMenu));
                orderMenu // 주문 메뉴 옵션 금액 추가 (옵션)
                        .updateTotalPrice(orderMenuOption.getPrice());
                order // 주문 총액 업데이트 (메뉴 옵션)
                        .updateTotalPrice(orderMenuOption.getPrice()); // 옵션별 금액
                orderMenuOptions // 주문 메뉴 옵션 컬랙션 생성 (주문 + 메뉴 옵션)
                        .add(orderMenuOption);
            }
            orderMenu // 주문 메뉴 금액 추가 (메뉴)
                    .updateTotalPrice(orderMenu.getPrice());
            order // 주문 총액 업데이트 (메뉴)
                    .updateTotalPrice(orderMenu.getPrice());
            orderMenu // 주문 메뉴 총액 업데이트 (갯수 처리)
                    .setTotalPrice(orderMenu.getTotalPrice() * orderMenu.getQty());
            orderMenus // 주문 메뉴 컬랙션 생성 (주문 + 메뉴)
                    .add(orderMenu);
        }
        orderRepository.save(order); // DB insert

        return new UserResponse.OrderDTO(order, orderMenus, orderMenuOptions);
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
