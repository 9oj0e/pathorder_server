package shop.project.pathorderserver.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuRepository;
import shop.project.pathorderserver.menu.Option;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    // 매장 목록보기
    public List<StoreResponse.ListingsDTO> getStoreList() {
        List<Store> stores = storeRepository.findAll();

        return stores.stream().map(StoreResponse.ListingsDTO::new).toList();
    }

    // 매장 상세보기
    public StoreResponse.DetailDTO getStoreDetail(int storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));

        return new StoreResponse.DetailDTO(store);
    }

    // 매장 상세보기 - 사업자 정보
    public StoreResponse.BizInfoDTO getStoreBizDetail(int storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));

        return new StoreResponse.BizInfoDTO(store);
    }

    // 매장 메뉴보기
    public StoreResponse.MenuListDTO getStoreMenuList(int storeId) {
        Store store // 매장 정보
                = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));
        List<Menu> menus // 매장 메뉴 정보
                = menuRepository.findAllMenuByStoreId(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 메뉴입니다."));

        return new StoreResponse.MenuListDTO(store, menus);
    }

    // 매장 메뉴 옵션보기
    public StoreResponse.MenuOptionDTO getStoreMenuDetail(int storeId, int menuId) {
        Store store // 매장 정보
                = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));
        Menu menu // 매장 메뉴 정보
                = menuRepository.findById(menuId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 메뉴입니다."));
        List<Option> optionList // 매장 메뉴 옵션 정보
                = menuRepository.findOptionByMenuId(menuId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 옵션입니다."));

        return new StoreResponse.MenuOptionDTO(store, menu, optionList);
    }
}
