package shop.project.pathorderserver.store;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class StoreOwnerController {
    private final StoreService storeService;
    private final HttpSession session;

    // 매장등록
    @PostMapping("/stores/join") // TODO: 이름 고치기, return
    public String join(StoreRequest.매장등록 reqDTO) {
        storeService.매장등록(reqDTO);

        return "";
    }

    // 점주 로그인
    @PostMapping("/stores/login")
    public String login(StoreRequest.로그인 reqDTO) {
        Store sessionUser = storeService.로그인(reqDTO);
        session.setAttribute("sessionUser", sessionUser);

        return "";
    }

    // 매장 주문내역 목록보기
    @GetMapping("/stores/{storeId}/orders")
    private String orderList(@PathVariable int storeId, Model model) {
        StoreResponse.OrderListDTO orderList = storeService.getOrderList(storeId);
        model.addAttribute("OrderList", orderList);

        return "";
    }

    // 매장 주문내역 상세보기
    @GetMapping("/stores/{storeId}/orders/{orderId}")
    private String orderDetail(@PathVariable int orderId, Model model) {
        StoreResponse.OrderDetailDTO orderDetail = storeService.getOrderDetail(orderId);
        model.addAttribute("orderDetail", orderDetail);

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
