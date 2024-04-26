package shop.project.pathorderserver.store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public List<StoreResponse.ListingsDTO> getStoreList() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(StoreResponse.ListingsDTO::new).toList();
    }
}
