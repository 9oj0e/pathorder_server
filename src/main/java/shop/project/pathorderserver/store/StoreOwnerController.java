package shop.project.pathorderserver.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StoreOwnerController {
    @GetMapping("/stores/{storeId}/orders") // TODO: 매장 주문내역 목록보기
    private String orderList(@PathVariable int storeId) {

        return "";
    }

    @GetMapping("/stores/{storeId}/orders/{orderId}") // TODO: 매장 주문내역 상세보기
    private String orderDetail(@PathVariable int orderId) {

        return "";
    }

    @GetMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 메뉴 목록보기
    private String menuList(@PathVariable int menuId) {

        return "";
    }

    @GetMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 메뉴 상세보기
    private String menuDetail(@PathVariable int menuId) {

        return "";
    }
}
