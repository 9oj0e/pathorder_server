package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuRepository;
import shop.project.pathorderserver.menu.Option;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

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
}
