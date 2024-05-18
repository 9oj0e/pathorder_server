package shop.project.pathorderserver.store;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;
import shop.project.pathorderserver.user.SessionUser;
import shop.project.pathorderserver.user.UserRequest;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreController {
    private final StoreService storeService;
    private final HttpSession session;

    @PostMapping("/api/stores")
    public ResponseEntity<?> storeList(@RequestBody UserRequest.LocationReqDTO reqDTO) { // 매장 목록보기
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        List<StoreResponse.StoreListDTO> respDTO = storeService.getStoreList(sessionUser.getId(), reqDTO.getLatitude(), reqDTO.getLongitude());
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/stores/{storeId}") // 매장 상세보기
    public ResponseEntity<?> storeInfo(@PathVariable int storeId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        StoreResponse.StoreInfoDTO respDTO = storeService.getStoreInfo(sessionUser.getId(), storeId);
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
