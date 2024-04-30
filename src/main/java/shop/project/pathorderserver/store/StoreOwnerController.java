package shop.project.pathorderserver.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class StoreOwnerController {
    private final StoreService storeService;

    // 매장등록
    @PostMapping("/stores/join") // TODO: 이름 고치기, return
    public String join(StoreRequest.매장등록 reqDTO) {
        storeService.매장등록(reqDTO);

        return "";
    }

    @GetMapping("/stores/{storeId}/orders") // TODO: 매장 주문내역 목록보기
    private String orderList(@PathVariable int storeId) {

        return "";
    }

    @GetMapping("/stores/{storeId}/orders/{orderId}") // TODO: 매장 주문내역 상세보기
    private String orderDetail(@PathVariable int orderId) {

        return "";
    }

    @GetMapping("/stores/{storeId}/menus") // TODO: 매장 메뉴 목록보기
    private String menuList(@PathVariable int menuId) {

        return "";
    }

    @GetMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 메뉴 상세보기
    private String menuDetail(@PathVariable int menuId) {

        return "";
    }


    @PutMapping("/stores/{storeId}/orders") // TODO: 주문 수정 (매장 측: 주문 상태 변경(조리중, 조리완료..)
    private String updateOrder(@PathVariable int storeId) {

        return "";
    }
}
