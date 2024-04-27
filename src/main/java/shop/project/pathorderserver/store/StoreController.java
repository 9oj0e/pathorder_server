package shop.project.pathorderserver.store;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import shop.project.pathorderserver._core.utils.ApiUtil;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreController {
    private final StoreService storeService;

    // 매장 목록 보기
    @GetMapping("/api/stores")
    public ResponseEntity<?> listings() {
        List<StoreResponse.ListingsDTO> responseDTO = storeService.getStoreList();
        
        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }

    // 매장 상세 보기
    @GetMapping("/api/stores/{storeId}")
    public ResponseEntity<?> storeInfo(@PathVariable Integer storeId) {
        StoreResponse.DetailDTO responseDTO = storeService.getStoreDetail(storeId);
        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }
}
