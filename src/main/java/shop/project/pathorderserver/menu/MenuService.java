package shop.project.pathorderserver.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    // 매장별 메뉴 리스트
    public List<MenuResponse.StoreMenuDTO> getAllStoreMenu(int storeId) {
        List<Menu> menuList = menuRepository.findAllMenuByStoreId(storeId).get(); // TODO: orElseThrow
        return menuList.stream().map(menu -> new MenuResponse.StoreMenuDTO(menu)).toList();
    }
}
