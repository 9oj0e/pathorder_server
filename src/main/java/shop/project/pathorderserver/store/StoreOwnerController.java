package shop.project.pathorderserver.store;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class StoreOwnerController {
    private final HttpSession session;
    private final StoreService storeService;

    /*------------------------------------------------------------------------------------- 회원가입 -----------------*/

    // 매장 관리자 - 회원가입약관
    @GetMapping("/stores/join-terms") // TODO: 매장 관리자 회원가입약관
    public String joinTermsForm() {
//        storeService.createStore(reqDTO);

        return "join-terms";
    }

    // 매장 관리자 - 회원가입폼
    @GetMapping("/stores/join-form") // TODO: 매장 관리자 회원가입폼
    public String joinForm() {
//        storeService.createStore(reqDTO);

        return "join-form";
    }

    // 매장 관리자 - 회원가입
    @PostMapping("/stores/join") // TODO: 매장 관리자 회원가입
    public String join(StoreRequest.JoinDTO reqDTO) {
        storeService.createStore(reqDTO);

        return "redirect:/stores/login-form";
    }

    /*------------------------------------------------------------------------------------- 로그인 -----------------*/
    // 매장 관리자 - 로그인폼
    @GetMapping("/stores/login-form") // TODO: 매장 관리자 - 로그인폼
    public String loginForm() {
//        SessionStore sessionStore = storeService.getStore(reqDTO);
//        session.setAttribute("sessionStore", sessionStore);

        return "login-form";
    }

    // 매장 관리자 - 로그인
    @PostMapping("/stores/login") // TODO: 매장 관리자 - 로그인
    public String login(StoreRequest.LoginDTO reqDTO) {
        SessionStore sessionStore = storeService.getStore(reqDTO);
        session.setAttribute("sessionStore", sessionStore);

        return "redirect:/";
    }

    /*------------------------------------------------------------------------------------- 메인페이지 -----------------*/

    // 메인페이지
    @GetMapping("/") // index
    private String index(Model model) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");

        if (sessionStore != null) {
            /*
            int storeId = sessionStore.getId();
            StoreResponse.OrderListDTO respDTO = storeService.getOrderList(storeId);
            model.addAttribute("orders", respDTO);

            return "orders";
            */
            return "redirect:/stores/" + sessionStore.getId() + "/orders";
        } else {
            return "redirect:/stores/login-form";
        }
    }

    // 매장 관리자 - 현재 접수된 주문
    @GetMapping("/stores/{storeId}/orders") // TODO: 매장 관리자 - 현재 접수된 주문
    private String orders(@PathVariable int storeId, Model model) { // index
        /*
        // TODO: 권한 처리
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (sessionStore != null) {
            return "orders";
        } else {
            return "login-form";
        }
        */

        HashMap<String, Object> currentOrderListDTO = storeService.getCurrentOrders(storeId);

        model.addAttribute("orderList", currentOrderListDTO);
        System.out.println(currentOrderListDTO);

        return "orders";
    }

    // 매장 관리자 - 주문내역 상세보기(모달) - ajax 통신을 해야함
    @ResponseBody
    @GetMapping("/stores/{storeId}/orders/{orderId}") // TODO: 매장 관리자 - 주문내역 상세보기(모달)
    private ResponseEntity<?> orderDetail(@PathVariable int storeId, @PathVariable int orderId) {
        // TODO: 권한 처리
        StoreResponse.OrderDetailDTO respDTO = storeService.getOrderDetail(orderId);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 매장 관리자 - 주문 상태 업데이트(주문 접수하기 -> 조리완료)
    @PostMapping("/stores/{storeId}/orders/{orderId}/update") // TODO: 매장 관리자 - 주문 상태 업데이트(주문 접수하기 -> 조리완료)
    private String updateOrder(@PathVariable int storeId, @PathVariable int orderId, StoreRequest.UpdateOrderDTO reqDTO) {
        // TODO: 권한 처리

        return "";
    }

    /*------------------------------------------------------------------------------------- 지난주문 -----------------*/

    // 매장 관리자 - 주문내역 목록보기
    @GetMapping("/stores/{storeId}/orders/history") // TODO: 매장 관리자 - 주문내역 목록보기
    private String orderList(@PathVariable int storeId, Model model) {
        // TODO: 권한 처리
        StoreResponse.OrderListDTO respDTO = storeService.getOrderList(storeId);
        model.addAttribute("orderList", respDTO);

        return "orders-list";
    }

    // 매장 관리자 - 주문내역 날짜로 조회
    @GetMapping("/stores/{storeId}/orders/history/date")
    public String orderListByDate() { // TODO: 매개변수로 @PathVariable int storeId, @RequestParam("date") String date, Model model 넣기
        // TODO: 권한 처리
        // TODO: 날짜로 검색하는 기능 구현
        // StoreResponse.OrderListDTO respDTO = orderService.getOrderListByDate(storeId, date);
        // model.addAttribute("orders", respDTO);
        return "orders-list";
    }

    /*------------------------------------------------------------------------------------- 메뉴 -----------------*/

    // 매장 관리자 - 매장 메뉴 목록보기
    @GetMapping("/stores/{storeId}/menus") // TODO: 매장 메뉴 목록보기
    private String menuList(@PathVariable int storeId, Model model) {
        // TODO: 권한 처리
        StoreResponse.MenuListDTO respDTO = storeService.getMenuList(storeId);
        model.addAttribute("ownerMenuList", respDTO);

        return "menus";
    }

    // 매장 관리자 - 메뉴 상세보기
    @GetMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 관리자 - 메뉴 상세보기
    private String menuDetail(@PathVariable int storeId, @PathVariable int menuId, Model model) {
        // TODO: 권한 처리
        StoreResponse.MenuDetailDTO respDTO = storeService.getMenuDetail(menuId);
        model.addAttribute("menu", respDTO);

        return "";
    }

    // TODO: 메뉴를 등록(수정)할 때 옵션을 함께 등록하는데 컨트롤러에서 나뉘어야 하는지에 대한 논의가 필요할 듯
    // TODO(장현정): 별도의 컨트롤러가 없이 메뉴를 등록할 때 같이 등록되게 하고, 수정할 때는 옵션 부분을 reset처리를 해서 새로 등록하는 방향이 어떨까요?
    // 매장 관리자 - 메뉴 등록하기
    @PostMapping("/stores/{storeId}/menus") // TODO: 매장 관리자 - 메뉴 등록하기
    private String addMenu(@PathVariable int storeId, StoreRequest.CreateMenuDTO reqDTO, Model model) {
        // TODO: 권한 처리
        StoreResponse.CreateMenuDTO respDTO = storeService.createMenu(storeId, reqDTO);
        model.addAttribute("menu", respDTO);

        return "";
    }

    // 매장 관리자 - 메뉴 수정하기
    @PutMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 관리자 - 메뉴 수정하기
    private String updateMenu(@PathVariable int storeId, @PathVariable int menuId, StoreRequest.UpdateMenuDTO reqDTO, Model model) {
        // TODO: 권한 처리
        StoreResponse.UpdateMenuDTO respDTO = storeService.updateMenu(menuId, reqDTO);
        model.addAttribute("menu", respDTO);

        return "";
    }

    // 매장 관리자 - 메뉴 삭제하기
    @DeleteMapping("/stores/{storeId}/menus/{menuId}") // TODO: 매장 관리자 - 메뉴 삭제하기
    private String deleteMenu(@PathVariable int storeId, @PathVariable int menuId) {
        // TODO: 권한 처리
        storeService.deleteMenu(menuId);

        return "";
    }

    // 매장 관리자 - 메뉴 옵션 등록하기
    @PostMapping("/stores/{storeId}/menus/{menuId}/options") // TODO: 매장 관리자 - 메뉴 옵션 등록하기
    private String addMenuOption(@PathVariable int storeId, @PathVariable int menuId, StoreRequest.CreateMenuOptionDTO reqDTO) {
        // TODO: 권한 처리
        StoreResponse.CreateMenuOptionDTO respDTO = storeService.createMenuOption(menuId, reqDTO);

        return "";
    }

    /* 메뉴 옵션은 메뉴 상세보기에서 처리?
    @GetMapping("/stores/{storeId}/menus/{menuId}/options") // TODO: 매장 관리자 - 메뉴 옵션보기
    private String menuOptionList(@PathVariable int storeId, @PathVariable int menuId) {
        // TODO: 권한 처리

        return "";
    }
    */

    // 매장 관리자 - 메뉴 옵션 수정하기
    @PutMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // TODO: 매장 관리자 - 메뉴 옵션 수정하기
    private String updateMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId, StoreRequest.UpdateMenuOptionDTO reqDTO) {
        // TODO: 권한 처리
        StoreResponse.UpdateMenuOptionDTO respDTO = storeService.updateMenuOption(optionId, reqDTO);

        return "";
    }

    // 매장 관리자 - 메뉴 옵션 삭제하기
    @DeleteMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // TODO: 매장 관리자 - 메뉴 옵션 삭제하기
    private String deleteMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId) {
        // TODO: 권한 처리
        storeService.deleteMenuOption(optionId);

        return "";
    }

    /*------------------------------------------------------------------------------------- 매장정보 -----------------*/

    // 매장 관리자 - 매장 정보 보기
    @GetMapping("/stores/{storeId}") // TODO: 매장 관리자 - 매장 정보 보기
    public String detail(@PathVariable int storeId, Model model) {
        // TODO: 권한 처리
        StoreResponse.StoreDTO respDTO = storeService.getStoreDetail(storeId);
        model.addAttribute("storeDetail", respDTO);

        return "store";
    }

    // 매장 관리자 - 매장 정보 업데이트폼 보기
    @GetMapping("/stores/{storeId}/update-form") // TODO: 매장 관리자 - 매장 정보 업데이트폼 보기
    public String updateForm(@PathVariable int storeId) {
        // TODO: 권한 처리
//        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
//        storeService.getStoreDetail(sessionStore.getId());

        return "store-update-form";
    }

    // 매장 관리자 - 매장 정보 수정
    @PostMapping("/stores/{storeId}") // TODO: 매장 관리자 - 매장 정보 수정
    public String update(@PathVariable int storeId, StoreRequest.UpdateDTO reqDTO) {
        // TODO: 권한 처리
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        SessionStore newSessionStore = storeService.updateStore(sessionStore.getId(), reqDTO);

        return "";
    }
}
