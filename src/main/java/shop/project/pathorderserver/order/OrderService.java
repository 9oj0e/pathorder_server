package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public OrderResponse.MenuOptionDTO getStoreNameAndMenuAndMenuOption(int storeId, int menuId) {
        // 매장 이름
        Store store = storeRepository.findById(storeId).get();

        // 해당 메뉴
        Menu menu = menuRepository.findById(menuId).get();

        // 메뉴 옵션
        List<Option> optionList = menuRepository.findOptionByMenuId(menuId).get();

        return new OrderResponse.MenuOptionDTO(store, menu, optionList);
    }
}
