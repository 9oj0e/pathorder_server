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

    @GetMapping("/api/stores")
    public ResponseEntity<?> storeList() { // 매장 목록보기
        List<StoreResponse.StoreListDTO> respDTO = storeService.getStoreList();

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/stores/{storeId}") // 매장 상세보기
    public ResponseEntity<?> storeInfo(@PathVariable int storeId) {
        StoreResponse.StoreInfoDTO respDTO = storeService.getStoreInfo(storeId);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/stores/{storeId}/biz-info") // 매장 상세보기 - 사업자 정보 TODO: 상세보기랑 합치기?
    public ResponseEntity<?> storeBizInfo(@PathVariable int storeId) {
        StoreResponse.StoreBizInfoDTO respDTO = storeService.getStoreBizInfo(storeId);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/stores/{storeId}/menus") // 매장 메뉴보기
    private ResponseEntity<?> storeMenuList(@PathVariable int storeId) {
        StoreResponse.StoreMenuListDTO respDTO = storeService.getStoreMenuList(storeId);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/stores/{storeId}/menus/{menuId}") // 매장 메뉴 옵션보기
    private ResponseEntity<?> storeMenuDetail(@PathVariable int storeId, @PathVariable int menuId) {
        StoreResponse.StoreMenuOptionDTO respDTO = storeService.getStoreMenuDetail(storeId, menuId);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
