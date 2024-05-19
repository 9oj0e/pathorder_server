package shop.project.pathorderserver.store;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.errors.exception.Exception403;
import shop.project.pathorderserver._core.utils.ApiUtil;

import java.time.LocalDate;
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

    @GetMapping("/stores/join-form") // 매장 관리자 - 회원 가입 폼
    public String joinForm(@RequestParam(name = "termsAgreed", required = false, defaultValue = "false") boolean termsAgreed) {
        if (termsAgreed) { // 약관 동의
            return "join-form";
        } else { // 약관 미 동의
            return "join-terms";
        }
    }

    @PostMapping("/stores/join") // 매장 관리자 회원가입
    public String join(@Valid StoreRequest.JoinDTO reqDTO) {
        storeService.createStore(reqDTO);
        return "redirect:/stores/login-form";
    }

    /*------------------------------------------------------------------------------------- 로그인 --------------------*/
    @GetMapping("/stores/login-form") // 매장 관리자 - 로그인 폼
    public String loginForm() {
        return "login-form";
    }

    @PostMapping("/stores/login") // 매장 관리자 - 로그인
    public String login(@Valid StoreRequest.LoginDTO reqDTO) {
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

    @ResponseBody
    @GetMapping("/stores/{storeId}/pending-order-count")
    public ResponseEntity<?> getPendingOrderCount(@PathVariable int storeId) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        int pendingOrderCount = storeService.getPendingOrderCount(sessionStore.getId());
        return ResponseEntity.ok(new ApiUtil<>(pendingOrderCount));
    }

    @GetMapping("/stores/{storeId}/orders") // 매장 관리자 - 처리중인 주문
    private String orders(@PathVariable int storeId, Model model) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        HashMap<String, Object> respDTO = storeService.getOrders(sessionStore.getId());
        model.addAttribute("orders", respDTO);
        return "orders";
    }

    @PostMapping("/stores/{storeId}/orders/{orderId}/update") // 매장 관리자 - 주문 상태 업데이트(주문 접수하기 -> 조리완료)
    private String updateOrder(@PathVariable int storeId, @PathVariable int orderId, @Valid StoreRequest.UpdateOrderDTO reqDTO) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        storeService.updateOrder(orderId, reqDTO); // 가게 아이디로 order를 찾아 reqDTO를 넣어 update.
        return "redirect:/stores/" + sessionStore.getId() + "/orders"; // TODO: ajax 가능?
    }

    @ResponseBody
    @GetMapping("/stores/{storeId}/orders/{orderId}") // 매장 관리자 - 주문내역 상세보기 (modal)
    private ResponseEntity<?> orderDetail(@PathVariable int storeId, @PathVariable int orderId) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        StoreResponse.OrderDetailDTO respDTO = storeService.getOrderDetail(orderId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    /*------------------------------------------------------------------------------------- 지난 주문 ------------------*/

    @GetMapping("/stores/{storeId}/orders/history") // 매장 관리자 - 주문내역 목록보기
    private String orderList(@PathVariable int storeId, Model model) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        StoreResponse.OrderListDTO respDTO = storeService.getOrderList(storeId);
        model.addAttribute("orders", respDTO.getOrderList());
        // model.addAttribute("storeId", storeId);
        return "order-list";
    }

    @GetMapping("/stores/{storeId}/orders/history/date") // 매장 관리자 - 주문내역 날짜로 조회
    public ResponseEntity<?> orderListSortByDate(@PathVariable int storeId,
                                                 @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                 @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, Model model) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        StoreResponse.OrderListDTO respDTO = storeService.getOrderListByDate(storeId, startDate, endDate);
        model.addAttribute("orders", respDTO.getOrderList());
        model.addAttribute("storeId", sessionStore.getId());

        return ResponseEntity.ok().body(respDTO);
    }

    /*------------------------------------------------------------------------------------- 메뉴 ----------------------*/

    @GetMapping("/stores/{storeId}/menus") // 매장 메뉴 목록보기
    private String menuList(@PathVariable int storeId, Model model) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        StoreResponse.MenuListDTO respDTO = storeService.getMenuList(storeId);
        model.addAttribute("menuList", respDTO.getMenuList());
        return "menus";
    }

    @ResponseBody
    @GetMapping("/stores/{storeId}/menus/{menuId}") // 매장 관리자 - 메뉴 상세보기
    private ResponseEntity<?> menuDetail(@PathVariable int storeId, @PathVariable int menuId, Model model) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        StoreResponse.MenuDetailDTO respDTO = storeService.getMenuDetail(menuId);
        model.addAttribute("sessionStore", sessionStore);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/stores/{storeId}/menus") // 매장 관리자 - 메뉴 등록하기
    private String addMenu(@PathVariable int storeId, @Valid StoreRequest.CreateMenuDTO reqDTO) {
        // TODO: 유효성 검사 (금액)
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        StoreResponse.CreateMenuDTO respDTO = storeService.createMenu(sessionStore.getId(), reqDTO);
        return "redirect:/stores/" + sessionStore.getId() + "/menus";
    }

    @ResponseBody
    @PutMapping("/stores/{storeId}/menus/{menuId}") // 매장 관리자 - 메뉴 수정하기
    private ResponseEntity<?> updateMenu(@PathVariable int storeId, @PathVariable int menuId, @RequestBody @Valid StoreRequest.UpdateMenuDTO reqDTO) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        StoreResponse.UpdateMenuDTO respDTO = storeService.updateMenu(menuId, reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    /*------------------------------------------------------------------------------------- 매장 정보 ------------------*/

    @GetMapping("/stores/{storeId}") // 매장 관리자 - 매장 정보 보기
    public String detail(@PathVariable int storeId, Model model) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        StoreResponse.StoreDTO respDTO = storeService.getStoreDetail(sessionStore.getId());
        model.addAttribute("storeDetail", respDTO);
        return "store";
    }

    @GetMapping("/stores/{storeId}/update-form") // 매장 관리자 - 매장 정보 업데이트 폼
    public String updateForm(@PathVariable int storeId, Model model) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        StoreResponse.StoreDTO respDTO = storeService.getStoreDetail(sessionStore.getId());
        model.addAttribute("storeDetail", respDTO);
        return "store-update-form";
    }

    @PostMapping("/stores/{storeId}") // 매장 관리자 - 매장 정보 수정
    public String update(@PathVariable int storeId, @Valid StoreRequest.UpdateDTO reqDTO) {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (storeId != sessionStore.getId()) {
            throw new Exception403("권한이 없습니다.");
        }
        SessionStore newSessionStore = storeService.updateStore(sessionStore.getId(), reqDTO);
        session.setAttribute("sessionStore", newSessionStore);
        return "redirect:/stores/" + sessionStore.getId();
    }
}
