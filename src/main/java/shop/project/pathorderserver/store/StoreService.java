package shop.project.pathorderserver.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public List<StoreResponse.ListingsDTO> getStoreList() {
        List<Store> stores = storeRepository.findAll();

        return stores.stream().map(StoreResponse.ListingsDTO::new).toList();
    }

    public StoreResponse.DetailDTO getStoreDetail(Integer storeId){
        // TODO: Exception 쪼개서 만들어야 함!!
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("해당하는 매장이 존재하지 않습니다."));

        return new StoreResponse.DetailDTO(store);
    }

    public StoreResponse.BizInfoDTO getBizInfo(Integer storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("해당하는 매장이 존재하지 않습니다."));

        return new StoreResponse.BizInfoDTO(store);
    }
}
