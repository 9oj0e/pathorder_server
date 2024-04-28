package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuRepository;
import shop.project.pathorderserver.menu.Option;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final OrderOptionRepository orderOptionRepository;
    // 메뉴 옵션
    public OrderResponse.MenuOptionDTO getStoreNameAndMenuAndMenuOption(int storeId, int menuId) {
        // 매장 이름
        Store store = storeRepository.findById(storeId).get();

        // 해당 메뉴
        Menu menu = menuRepository.findById(menuId).get();

        // 메뉴 옵션
        List<Option> optionList = menuRepository.findOptionByMenuId(menuId).get();

        return new OrderResponse.MenuOptionDTO(store, menu, optionList);
    }

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

    @Transactional
    public OrderResponse.SaveDTO createOrder(OrderRequest.SaveDTO reqDTO) {
        Order order = new Order(reqDTO);
        // 주문 저장
        Order savedOrder = orderRepository.save(order);

        // 메뉴 리스트
        List<OrderMenu> orderMenuList = reqDTO.getOrderMenuList() != null ?
                reqDTO.getOrderMenuList().stream()
                        .map(menu -> new OrderMenu(menu, savedOrder))
                        .toList() :
                Collections.emptyList();
        // 옵션 리스트
        List<OrderOption> orderOptionList = reqDTO.getOrderMenuList() != null ?
                reqDTO.getOrderMenuList().stream()
                        .flatMap(menu -> {
                            OrderMenu savedOrderMenu = orderMenuRepository.save(new OrderMenu(menu, savedOrder));
                            return menu.getOrderOptionList().stream()
                                    .map(optionDTO -> new OrderOption(optionDTO, savedOrder, savedOrderMenu));
                        })
                        .toList() :
                Collections.emptyList();

        // 주문 메뉴 저장
        orderMenuList.forEach(orderMenu -> orderMenuRepository.save(orderMenu));
        // 주문 옵션 저장
        orderOptionList.forEach(orderOption -> orderOptionRepository.save(orderOption));

        return new OrderResponse.SaveDTO(savedOrder, orderMenuList, orderOptionList);
    }
}