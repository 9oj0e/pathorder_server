package shop.project.pathorderserver.store;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class StoreOwnerController {
    private final StoreService storeService;
    private final HttpSession session;

    @PostMapping("/stores/join") // 매장 회원가입
    public String join(StoreRequest.JoinDTO reqDTO) {
        storeService.createStore(reqDTO);

        return "";
    }

    @PostMapping("/stores/login") // 매장 로그인
    public String login(StoreRequest.LoginDTO reqDTO) {
        SessionStore sessionStore = storeService.getStore(reqDTO);
        session.setAttribute("sessionStore", sessionStore);

        return "";
    }

    @GetMapping("/stores/{storeId}") // TODO: 매장 정보 보기
    public String info() {

        return "";
    }

    @PutMapping("/stores/{storeId}") // TODO: 매장 정보 수정
    public String update() {

        return "";
    }

    @GetMapping("/stores/{storeId}/orders") // 매장 주문내역 목록보기
    private String orderList(@PathVariable int storeId, Model model) {
        StoreResponse.OrderListDTO orderList = storeService.getOrderList(storeId);
        model.addAttribute("orderList", orderList);

        return "";
    }

    @GetMapping("/stores/{storeId}/orders/{orderId}") // 매장 주문내역 상세보기
    private String orderDetail(@PathVariable int orderId, Model model) {
        StoreResponse.OrderDetailDTO orderDetail = storeService.getOrderDetail(orderId);
        model.addAttribute("orderDetail", orderDetail);

        return "";
    }

    @PutMapping("/stores/{storeId}/orders/{orderId}") // TODO: 주문 수정 (매장 측: 주문 상태 변경(조리중, 조리완료..)
    private String updateOrder(@PathVariable int storeId, @PathVariable int orderId) {

        return "";
    }

    @PostMapping("/stores/{storeId}/menus") // TODO: 매장 메뉴 등록하기
    private String addMenu(@PathVariable int storeId) {

        return "";
    }

    @GetMapping("/stores/{storeId}/menus") // 매장 메뉴 목록보기
    private String menuList(@PathVariable int storeId, Model model) {
        StoreResponse.OwnerMenuListDTO respDTO = storeService.getOwnerMenuList(storeId);
        model.addAttribute("ownerMenuList", respDTO);

        return "";
    }

    @GetMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 메뉴 상세보기
    private String menuDetail(@PathVariable int storeId, @PathVariable int menuId) {

        return "";
    }

    @PutMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 메뉴 수정하기
    private String updateMenu(@PathVariable int storeId, @PathVariable int menuId) {

        return "";
    }

    @DeleteMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 메뉴 삭제하기
    private String deleteMenu(@PathVariable int storeId, @PathVariable int menuId) {

        return "";
    }

    @PostMapping("/stores/{storeId}/menus/{menuId}/options") // TODO: 매장 메뉴 옵션 등록하기
    private String addMenuOption(@PathVariable int storeId, @PathVariable int menuId) {

        return "";
    }

    @GetMapping("/stores/{storeId}/menus/{menuId}/options") // TODO: 매장 메뉴 옵션보기
    private String menuOptionList(@PathVariable int storeId, @PathVariable int menuId) {

        return "";
    }

    @PutMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // TODO: 매장 메뉴 옵션 수정
    private String updateMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId) {

        return "";
    }

    @DeleteMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // TODO: 매장 메뉴 옵션 삭제
    private String deleteMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId) {

        return "";
    }
}
