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
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        List<Store> stores = storeRepository.findAll(sort);
        return stores.stream().map(store -> new StoreResponse.ListingsDTO(store)).toList();
    }
}
