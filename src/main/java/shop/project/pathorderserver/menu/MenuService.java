package shop.project.pathorderserver.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    // 매장 메뉴
    public MenuResponse.StoreMenuDTO getStoreNameAndStoreMenu(int storeId) {
        // 매장 이름
        Store store = storeRepository.findById(storeId).get();

        // 매장 메뉴
        List<Menu> menuList = menuRepository.findAllMenuByStoreId(storeId).get();

        return new MenuResponse.StoreMenuDTO(store, menuList);
    }

    // 메뉴별 옵션 리스트
    public List<MenuResponse.OptionDTO> getOption(int menuId) {
        List<Option> optionList = menuRepository.findOptionByMenuId(menuId).get(); // TODO: orElseThrow
        return optionList.stream().map(option -> new MenuResponse.OptionDTO(option)).toList();
    }
}
