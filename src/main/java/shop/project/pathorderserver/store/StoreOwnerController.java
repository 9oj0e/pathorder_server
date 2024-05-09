package shop.project.pathorderserver.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;

import java.util.HashMap;

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

    /*------------------------------------------------------------------------------------- 회원가입 -------------------*/

    @PostMapping("/stores/join-form") // TODO: 매장 관리자 - 회원 가입 폼
    public String joinForm(boolean termsAgreed) {

        if (termsAgreed) { // 약관 동의
            return "join-form";
        } else { // 약관 미 동의
            return "join-terms";
        }
    }

    @PostMapping("/stores/join") // 매장 관리자 회원가입
    public String join(StoreRequest.JoinDTO reqDTO) {
        storeService.createStore(reqDTO);

        return "redirect:/stores/login-form";
    }

    /*------------------------------------------------------------------------------------- 로그인 --------------------*/
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

    @GetMapping("stores/logout") // 매장 관리자 - 로그아웃
    public String logout() {
        session.invalidate();

        return "redirect:/";
    }

    /*------------------------------------------------------------------------------------- 메인 페이지 -----------------*/

    @GetMapping("/stores/{storeId}/orders") // 매장 관리자 - 처리중인 주문
    private String orders(@PathVariable int storeId, Model model) {
        HashMap<String, Object> respDTO = storeService.getOrders(storeId);
        model.addAttribute("orders", respDTO);

        return "orders";
    }

    @PostMapping("/stores/{storeId}/orders/{orderId}/update") // 매장 관리자 - 주문 상태 업데이트(주문 접수하기 -> 조리완료)
    private String updateOrder(@PathVariable int storeId, @PathVariable int orderId, StoreRequest.UpdateOrderDTO reqDTO) {
        // TODO: 권한 처리
        storeService.updateOrder(orderId, reqDTO); // 가게 아이디로 order를 찾아 reqDTO를 넣어 update.

        return "redirect:/stores/" + storeId + "/orders"; // TODO: ajax 가능?
    }

    @ResponseBody
    @GetMapping("/stores/{storeId}/orders/{orderId}") // 매장 관리자 - 주문내역 상세보기 (modal)
    private ResponseEntity<?> orderDetail(@PathVariable int storeId, @PathVariable int orderId) {
        // TODO: 권한 처리
        StoreResponse.OrderDetailDTO respDTO = storeService.getOrderDetail(orderId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    /*------------------------------------------------------------------------------------- 지난 주문 ------------------*/

    @GetMapping("/stores/{storeId}/orders/history") // 매장 관리자 - 주문내역 목록보기
    private String orderList(@PathVariable int storeId, Model model) {
        // TODO: 권한 처리
        StoreResponse.OrderListDTO respDTO = storeService.getOrderList(storeId);
        model.addAttribute("orderList", respDTO.getOrderList());

        return "orders-list";
    }

    @GetMapping("/stores/{storeId}/orders/history/date") // 매장 관리자 - 주문내역 날짜로 조회
    public String orderListSortByDate(@PathVariable int storeId, @RequestParam("date") String date, Model model) {
        // TODO: 권한 처리
        // TODO: 날짜로 검색하는 기능 구현
        // StoreResponse.OrderListDTO respDTO = orderService.getOrderListByDate(storeId, date);
        // model.addAttribute("orders", respDTO);

        return "orders-list";
    }

    /*------------------------------------------------------------------------------------- 메뉴 ----------------------*/

    @GetMapping("/stores/{storeId}/menus") // 매장 메뉴 목록보기
    private String menuList(@PathVariable int storeId, Model model) {
        StoreResponse.MenuListDTO respDTO = storeService.getMenuList(storeId);
        model.addAttribute("menuList", respDTO.getMenuList());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonMenuList = null;
//        try {
//            jsonMenuList = objectMapper.writeValueAsString(respDTO);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        model.addAttribute("jsonMenuList", jsonMenuList);

        return "menus";
    }

    @ResponseBody
    @GetMapping("/stores/{storeId}/menus/{menuId}") // 매장 관리자 - 메뉴 상세보기
    private ResponseEntity<?> menuDetail(@PathVariable int storeId, @PathVariable int menuId) {
        StoreResponse.MenuDetailDTO respDTO = storeService.getMenuDetail(menuId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/stores/{storeId}/menus") // 매장 관리자 - 메뉴 등록하기
    private String addMenu(@PathVariable int storeId, StoreRequest.CreateMenuDTO reqDTO) {
        // TODO: 유효성 검사 (금액)
        StoreResponse.CreateMenuDTO respDTO = storeService.createMenu(storeId, reqDTO);

        return "redirect:/stores/" + storeId + "/menus";
    }

    @ResponseBody
    @PutMapping("/stores/{storeId}/menus/{menuId}") // 매장 관리자 - 메뉴 수정하기
    private ResponseEntity<?> updateMenu(@PathVariable int storeId, @PathVariable int menuId, @RequestBody StoreRequest.UpdateMenuDTO reqDTO, Model model) {
        System.out.println("+++++++++" + reqDTO);
        StoreResponse.UpdateMenuDTO respDTO = storeService.updateMenu(menuId, reqDTO);
        model.addAttribute("menu", respDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    /*
    @DeleteMapping("/stores/{storeId}/menus/{menuId}") // 매장 관리자 - 메뉴 삭제하기
    private String deleteMenu(@PathVariable int storeId, @PathVariable int menuId) {
        storeService.deleteMenu(menuId);

        return "";
    }

    @PostMapping("/stores/{storeId}/menus/{menuId}/options") // 매장 관리자 - 메뉴 옵션 등록하기
    private String addMenuOption(@PathVariable int storeId, @PathVariable int menuId, StoreRequest.CreateMenuOptionDTO reqDTO) {
        StoreResponse.CreateMenuOptionDTO respDTO = storeService.createMenuOption(menuId, reqDTO);

        return "";
    }

    @PutMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // 매장 관리자 - 메뉴 옵션 수정하기
    private String updateMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId, StoreRequest.UpdateMenuOptionDTO reqDTO) {
        StoreResponse.UpdateMenuOptionDTO respDTO = storeService.updateMenuOption(optionId, reqDTO);

        return "";
    }

    @DeleteMapping("/stores/{storeId}/menus/{menuId}/options/{optionId}") // 매장 관리자 - 메뉴 옵션 삭제하기
    private String deleteMenuOption(@PathVariable int storeId, @PathVariable int menuId, @PathVariable int optionId) {
        storeService.deleteMenuOption(optionId);

        return "";
    }
    */
    /*------------------------------------------------------------------------------------- 매장 정보 ------------------*/

    @GetMapping("/stores/{storeId}") // 매장 관리자 - 매장 정보 보기
    public String detail(@PathVariable int storeId, Model model) {
        StoreResponse.StoreDTO respDTO = storeService.getStoreDetail(storeId);
        model.addAttribute("storeDetail", respDTO);

        return "store";
    }

    @GetMapping("/stores/{storeId}/update-form") // 매장 관리자 - 매장 정보 업데이트 폼
    public String updateForm(@PathVariable int storeId, Model model) {
        // SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        StoreResponse.StoreDTO respDTO = storeService.getStoreDetail(storeId);
        model.addAttribute("storeDetail", respDTO);

        return "store-update-form";
    }

    @PostMapping("/stores/{storeId}") // 매장 관리자 - 매장 정보 수정
    public String update(@PathVariable int storeId, StoreRequest.UpdateDTO reqDTO) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        SessionStore newSessionStore = storeService.updateStore(sessionStore.getId(), reqDTO);

        return "redirect:/stores/" + storeId;
    }
}
