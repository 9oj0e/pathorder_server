package shop.project.pathorderserver.store;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class StoreOwnerController {
    private final HttpSession session;
    private final StoreService storeService;

    @PostMapping("/stores/join") // TODO: 매장 관리자 회원가입
    public String join(StoreRequest.JoinDTO reqDTO) {
        storeService.createStore(reqDTO);

        return "";
    }

    @PostMapping("/stores/login") // TODO: 매장 관리자 - 로그인
    public String login(StoreRequest.LoginDTO reqDTO) {
        SessionStore sessionStore = storeService.getStore(reqDTO);
        session.setAttribute("sessionStore", sessionStore);

        return "";
    }

    @GetMapping("/stores/{storeId}") // TODO: 매장 관리자 - 매장 정보 보기
    public String detail(@PathVariable int storeId) {
        // TODO: 권한 처리
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        storeService.getStoreDetail(sessionStore.getId());

        return "";
    }

    @PutMapping("/stores/{storeId}") // TODO: 매장 관리자 - 매장 정보 수정
    public String update(@PathVariable int storeId, StoreRequest.UpdateDTO reqDTO) {
        // TODO: 권한 처리
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        SessionStore newSessionStore = storeService.updateStore(sessionStore.getId(), reqDTO);

        return "";
    }

    @PostMapping("/stores/{storeId}/menus") // TODO: 매장 관리자 - 메뉴 등록하기
    private String addMenu(@PathVariable int storeId, StoreRequest.CreateMenuDTO reqDTO, Model model) {
        // TODO: 권한 처리
        StoreResponse.CreateMenuDTO respDTO = storeService.createMenu(reqDTO);
        model.addAttribute("menu", respDTO);

        return "";
    }

    @GetMapping("/stores/{storeId}/menus") // TODO: 매장 메뉴 목록보기
    private String menuList(@PathVariable int storeId, Model model) {
        // TODO: 권한 처리
        StoreResponse.OwnerMenuListDTO respDTO = storeService.getMenuList(storeId);
        model.addAttribute("ownerMenuList", respDTO);

        return "";
    }

    @GetMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 관리자 - 메뉴 상세보기
    private String menuDetail(@PathVariable int storeId, @PathVariable int menuId, Model model) {
        // TODO: 권한 처리
        StoreResponse.MenuDTO respDTO = storeService.getMenuDetail(menuId);
        model.addAttribute("menu", respDTO);

        return "";
    }

    @PutMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 관리자 - 메뉴 수정하기
    private String updateMenu(@PathVariable int storeId, @PathVariable int menuId, StoreRequest.UpdateMenuDTO reqDTO, Model model) {
        // TODO: 권한 처리
        StoreResponse.UpdateMenuDTO respDTO = storeService.updateMenu(reqDTO);
        model.addAttribute("menu", respDTO);

        return "";
    }

    @DeleteMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 관리자 - 메뉴 삭제하기
    private String deleteMenu(@PathVariable int storeId, @PathVariable int menuId) {
        // TODO: 권한 처리
        storeService.deleteMenu(menuId);

        return "";
    }

    @PostMapping("/stores/{storeId}/menus/{menuId}/options") // TODO: 매장 관리자 - 메뉴 옵션 등록하기
    private String addMenuOption(@PathVariable int storeId, @PathVariable int menuId, StoreRequest.CreateMenuOptionDTO reqDTO) {
        // TODO: 권한 처리
        StoreResponse.CreateMenuOptionDTO respDTO = storeService.createMenuOption(reqDTO);

        return "";
    }

    /* 메뉴 옵션은 메뉴 상세보기에서 처리?
    @GetMapping("/stores/{storeId}/menus/{menuId}/options") // TODO: 매장 관리자 - 메뉴 옵션보기
    private String menuOptionList(@PathVariable int storeId, @PathVariable int menuId) {
        // TODO: 권한 처리

        return "";
    }
    */
    @PutMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // TODO: 매장 관리자 - 메뉴 옵션 수정하기
    private String updateMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId, StoreRequest.UpdateMenuOptionDTO reqDTO) {
        // TODO: 권한 처리
        StoreResponse.UpdateMenuOptionDTO respDTO = storeService.updateMenuOption(reqDTO);

        return "";
    }

    @DeleteMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // TODO: 매장 관리자 - 메뉴 옵션 삭제하기
    private String deleteMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId) {
        // TODO: 권한 처리
        storeService.deleteMenuOption(optionId);

        return "";
    }

    @GetMapping("/stores/{storeId}/orders") // TODO: 매장 관리자 - 주문내역 목록보기
    private String orderList(@PathVariable int storeId, Model model) {
        // TODO: 권한 처리
        StoreResponse.OrderListDTO respDTO = storeService.getOrderList(storeId);
        model.addAttribute("orderList", respDTO);

        return "";
    }

    @GetMapping("/stores/{storeId}/orders/{orderId}") // TODO: 매장 관리자 - 주문내역 상세보기
    private String orderDetail(@PathVariable int orderId, Model model) {
        // TODO: 권한 처리
        StoreResponse.OrderDetailDTO respDTO = storeService.getOrderDetail(orderId);
        model.addAttribute("orderDetail", respDTO);

        return "";
    }

    @PutMapping("/stores/{storeId}/orders/{orderId}") // TODO: 매장 관리자 - 주문 업데이트
    private String updateOrder(@PathVariable int storeId, @PathVariable int orderId, StoreRequest.UpdateOrderDTO reqDTO) {
        // TODO: 권한 처리
        StoreResponse.UpdateOrderDTO respDTO = storeService.updateOrder(reqDTO);

        return "";
    }
}
