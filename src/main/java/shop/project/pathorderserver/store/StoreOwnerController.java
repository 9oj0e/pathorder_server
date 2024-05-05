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

    @GetMapping("/") // index
    private String index() {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");

        if (sessionStore != null) {
            return "redirect:/stores/" + sessionStore.getId() + "/orders";
        } else {
            return "redirect:/stores/login-form";
        }
    }

    /*------------------------------------------------------------------------------------- 회원가입 -----------------*/

    @GetMapping("/stores/join-terms") // 매장 관리자 회원가입 약관
    public String joinTermsForm() {
        // TODO: joinForm이랑 합치기

        return "join-terms";
    }

    @GetMapping("/stores/join-form") // 매장 관리자 회원가입폼
    public String joinForm() {

        return "join-form";
    }

    @PostMapping("/stores/join") // 매장 관리자 회원가입
    public String join(StoreRequest.JoinDTO reqDTO) {
        storeService.createStore(reqDTO);

        return "redirect:/stores/login-form";
    }

    /*------------------------------------------------------------------------------------- 로그인 -----------------*/
    @GetMapping("/stores/login-form") // 매장 관리자 - 로그인 폼
    public String loginForm() {

        return "login-form";
    }

    @PostMapping("/stores/login") // 매장 관리자 - 로그인
    public String login(StoreRequest.LoginDTO reqDTO) {
        SessionStore sessionStore = storeService.getStore(reqDTO);
        session.setAttribute("sessionStore", sessionStore);

        return "redirect:/";
    }

    /*------------------------------------------------------------------------------------- 메인페이지 -----------------*/

    @GetMapping("/stores/{storeId}/orders") // 매장 관리자 - 처리중인 주문
    private String orders(@PathVariable int storeId, Model model) {
        HashMap<String, Object> currentOrderListDTO = storeService.getCurrentOrders(storeId);
        model.addAttribute("orderList", currentOrderListDTO);
        System.out.println(currentOrderListDTO);

        return "orders";
    }

    @PostMapping("/stores/{storeId}/orders/{orderId}/update") // 매장 관리자 - 주문 상태 업데이트(주문 접수하기 -> 조리완료)
    private String updateOrder(@PathVariable int storeId, @PathVariable int orderId, StoreRequest.UpdateOrderDTO reqDTO) {
        // TODO: 권한 처리
        // 상태를 업데이트 하려면 가게 오더 특정 아이디에 해당하는 오더를 찾아서 그 오더의 상태를 업데이트 해주면 된다.
        storeService.updateOrder(orderId, reqDTO);
        return "redirect:/stores/" + storeId + "/orders";
    }

    @ResponseBody
    @GetMapping("/stores/{storeId}/orders/{orderId}") // 매장 관리자 - 주문내역 상세보기 (modal)
    private ResponseEntity<?> orderDetail(@PathVariable int storeId, @PathVariable int orderId) {
        // TODO: 권한 처리
        StoreResponse.OrderDetailDTO respDTO = storeService.getOrderDetail(orderId);

        return ResponseEntity.ok(new ApiUtil(respDTO)); // TODO: ajax 통신
    }

    /*------------------------------------------------------------------------------------- 지난주문 -----------------*/

    @GetMapping("/stores/{storeId}/orders/history") // 매장 관리자 - 주문내역 목록보기
    private String orderList(@PathVariable int storeId, Model model) {
        // TODO: 권한 처리
        StoreResponse.OrderListDTO respDTO = storeService.getOrderList(storeId);
        model.addAttribute("orderList", respDTO);

        return "orders-list";
    }

    @GetMapping("/stores/{storeId}/orders/history/date") // 매장 관리자 - 주문내역 날짜로 조회
    public String orderListSortByDate() { // TODO: 매개변수로 @PathVariable int storeId, @RequestParam("date") String date, Model model 넣기
        // TODO: 권한 처리
        // TODO: 날짜로 검색하는 기능 구현
        // StoreResponse.OrderListDTO respDTO = orderService.getOrderListByDate(storeId, date);
        // model.addAttribute("orders", respDTO);
        
        return "orders-list";
    }

    /*------------------------------------------------------------------------------------- 메뉴 -----------------*/

    @GetMapping("/stores/{storeId}/menus") // 매장 메뉴 목록보기
    private String menuList(@PathVariable int storeId, Model model) {
        // TODO: 권한 처리
        StoreResponse.MenuListDTO respDTO = storeService.getMenuList(storeId);
        model.addAttribute("ownerMenuList", respDTO);

        return "menus";
    }

    @GetMapping("/stores/{storeId}/menus/{menuId}") // 매장 관리자 - 메뉴 상세보기
    private String menuDetail(@PathVariable int storeId, @PathVariable int menuId, Model model) {
        // TODO: 권한 처리
        StoreResponse.MenuDetailDTO respDTO = storeService.getMenuDetail(menuId);
        model.addAttribute("menu", respDTO);

        return "";
    }

    // TODO: 메뉴를 등록(수정)할 때 옵션을 함께 등록하는데 컨트롤러에서 나뉘어야 하는지에 대한 논의가 필요할 듯
    // TODO(장현정): 별도의 컨트롤러가 없이 메뉴를 등록할 때 같이 등록되게 하고, 수정할 때는 옵션 부분을 reset처리를 해서 새로 등록하는 방향이 어떨까요?
    @PostMapping("/stores/{storeId}/menus") // 매장 관리자 - 메뉴 등록하기
    private String addMenu(@PathVariable int storeId, StoreRequest.CreateMenuDTO reqDTO, Model model) {
        // TODO: 권한 처리
        StoreResponse.CreateMenuDTO respDTO = storeService.createMenu(storeId, reqDTO);
        model.addAttribute("menu", respDTO);

        return "";
    }

    @PutMapping("/stores/{storeId}/menus/{menuId}") // 매장 관리자 - 메뉴 수정하기
    private String updateMenu(@PathVariable int storeId, @PathVariable int menuId, StoreRequest.UpdateMenuDTO reqDTO, Model model) {
        // TODO: 권한 처리
        StoreResponse.UpdateMenuDTO respDTO = storeService.updateMenu(menuId, reqDTO);
        model.addAttribute("menu", respDTO);

        return "";
    }

    @DeleteMapping("/stores/{storeId}/menus/{menuId}") // 매장 관리자 - 메뉴 삭제하기
    private String deleteMenu(@PathVariable int storeId, @PathVariable int menuId) {
        // TODO: 권한 처리
        storeService.deleteMenu(menuId);

        return "";
    }

    @PostMapping("/stores/{storeId}/menus/{menuId}/options") // 매장 관리자 - 메뉴 옵션 등록하기
    private String addMenuOption(@PathVariable int storeId, @PathVariable int menuId, StoreRequest.CreateMenuOptionDTO reqDTO) {
        // TODO: 권한 처리
        StoreResponse.CreateMenuOptionDTO respDTO = storeService.createMenuOption(menuId, reqDTO);

        return "";
    }

    @PutMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // 매장 관리자 - 메뉴 옵션 수정하기
    private String updateMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId, StoreRequest.UpdateMenuOptionDTO reqDTO) {
        // TODO: 권한 처리
        StoreResponse.UpdateMenuOptionDTO respDTO = storeService.updateMenuOption(optionId, reqDTO);

        return "";
    }

    @DeleteMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // 매장 관리자 - 메뉴 옵션 삭제하기
    private String deleteMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId) {
        // TODO: 권한 처리
        storeService.deleteMenuOption(optionId);

        return "";
    }

    /*------------------------------------------------------------------------------------- 매장정보 -----------------*/

    @GetMapping("/stores/{storeId}") // 매장 관리자 - 매장 정보 보기
    public String detail(@PathVariable int storeId, Model model) {
        // TODO: 권한 처리
        StoreResponse.StoreDTO respDTO = storeService.getStoreDetail(storeId);
        model.addAttribute("storeDetail", respDTO);

        return "store";
    }

    @GetMapping("/stores/{storeId}/update-form") // 매장 관리자 - 매장 정보 업데이트 폼
    public String updateForm(@PathVariable int storeId) {
        // TODO: 권한 처리
        // SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        StoreResponse.StoreDTO respDTO = storeService.getStoreDetail(storeId);
        model.addAttribute("storeDetail", respDTO);

        return "store-update-form";
    }

    @PostMapping("/stores/{storeId}") // 매장 관리자 - 매장 정보 수정
    public String update(@PathVariable int storeId, StoreRequest.UpdateDTO reqDTO) {
        // TODO: 권한 처리
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        SessionStore newSessionStore = storeService.updateStore(sessionStore.getId(), reqDTO);

        return "";
    }
}
