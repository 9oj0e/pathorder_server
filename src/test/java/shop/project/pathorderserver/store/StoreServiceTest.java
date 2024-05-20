package shop.project.pathorderserver.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.App404;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuOption;
import shop.project.pathorderserver.menu.MenuOptionRepository;
import shop.project.pathorderserver.menu.MenuRepository;
import shop.project.pathorderserver.order.Order;
import shop.project.pathorderserver.order.OrderRepository;
import shop.project.pathorderserver.order.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class StoreServiceTest {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuOptionRepository menuOptionRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test // 매장 관리자 - 회원가입
    void createStore_test() {
        // given
        StoreRequest.JoinDTO reqDTO = new StoreRequest.JoinDTO();
        reqDTO.setUsername("username123");
        reqDTO.setPassword("1234");
        reqDTO.setOwnerName("홍길동");
        reqDTO.setOwnerTel("01012340000");
        reqDTO.setOwnerEmail("username@email.com");
        reqDTO.setBizNum("123-12-12345");
        reqDTO.setName("매장이름");
        reqDTO.setTel("0510001234");
        reqDTO.setAddress("주소");
        // when
        StoreResponse.JoinDTO respDTO = storeService.createStore(reqDTO);
        // then
        Assertions.assertThat(respDTO.getOwnerName()).isEqualTo("홍길동");
    }

    @Test // 매장 관리자 - 로그인
    void getStore_test() {
        // given
        StoreRequest.LoginDTO reqDTO = new StoreRequest.LoginDTO();
        reqDTO.setUsername("jake1234");
        reqDTO.setPassword("1234");
        // when
        SessionStore sessionStore = storeService.getStore(reqDTO);
        // then
        Assertions.assertThat(sessionStore.getUsername()).isEqualTo("jake1234");
    }

    @Test // 매장 관리자 - 매장 정보 상세보기
    void getStoreDetail() {
        // given
        int storeId = 1;
        // when
        StoreResponse.StoreDTO respDTO = storeService.getStoreDetail(storeId);
        // then
        Assertions.assertThat(respDTO.getName()).isEqualTo("연의양과");
    }

    @Test // 매장 관리자 - 매장 정보 수정하기
    void updateStore_test() {
        // given
        int sessionId = 1;
        StoreRequest.UpdateDTO reqDTO = new StoreRequest.UpdateDTO();
        // reqDTO.setPassword("1234");
        reqDTO.setOwnerName("매장 정리");
        // when
        // 변경 전 확인
        // Store store = storeRepository.findById(sessionId).get();
        // System.out.println("_test: " + store.getUsername());
        SessionStore sessionStore = storeService.updateStore(sessionId, reqDTO);
        // then
        Assertions.assertThat(sessionStore.getOwnerName()).isEqualTo("매장 정리");
    }

    @Test // 매장 관리자 - 메뉴 등록하기
    void createMenu_test() {
        // given
        int storeId = 1;
        StoreRequest.CreateMenuDTO reqDTO = new StoreRequest.CreateMenuDTO();
        reqDTO.setPrice(3000);
        reqDTO.setCategory("과일 음료");
        reqDTO.setName("자몽에이드");
        reqDTO.setDescription("내가 제일 좋아함");
        // reqDTO.setImgFile(null);
        // when
        StoreResponse.CreateMenuDTO respDTO = storeService.createMenu(storeId, reqDTO);
        // then
        Assertions.assertThat(respDTO.getName()).isEqualTo("자몽에이드");
    }

    @Test // 매장 관리자 - 메뉴 상세보기
    void getMenuDetail_test() {
        // given
        int menuId = 1;
        // when
        StoreResponse.MenuDetailDTO respDTO = storeService.getMenuDetail(menuId);
        // then
        Assertions.assertThat(respDTO.getName()).isEqualTo("아메리카노");
        Assertions.assertThat(respDTO.getMenuOptionList().size()).isEqualTo(6);
        Assertions.assertThat(respDTO.getMenuOptionList().get(5).getPrice()).isEqualTo(500);
    }

    @Test // 매장 관리자 - 메뉴 목록보기
    void getMenuList_test() {
        // given
        int storeId = 1;
        // when
        StoreResponse.MenuListDTO respDTO = storeService.getMenuList(storeId);
        // then
        Assertions.assertThat(respDTO.getMenuList().size()).isEqualTo(5);
    }

    @Test // 매장 관리자 - 메뉴 수정하기
    void updateMenu_test() {
        // given
        int menuId = 1;
        StoreRequest.UpdateMenuDTO reqDTO = new StoreRequest.UpdateMenuDTO();
        reqDTO.setPrice(10000);
        StoreRequest.UpdateMenuDTO.MenuOptionDTO option1 = new StoreRequest.UpdateMenuDTO.MenuOptionDTO();
        option1.setPrice(700);
        option1.setName("a");
        option1.setRequired(true);
        StoreRequest.UpdateMenuDTO.MenuOptionDTO option2 = new StoreRequest.UpdateMenuDTO.MenuOptionDTO();
        option2.setPrice(500);
        option2.setName("b");
        option2.setRequired(false);
        StoreRequest.UpdateMenuDTO.MenuOptionDTO option3 = new StoreRequest.UpdateMenuDTO.MenuOptionDTO();
        option3.setPrice(0);
        option3.setName("c");
        option3.setRequired(true);
        List<StoreRequest.UpdateMenuDTO.MenuOptionDTO> menuOptionList = new ArrayList<>();
        menuOptionList.add(option1);
        menuOptionList.add(option2);
        menuOptionList.add(option3);
        reqDTO.setMenuOptions(menuOptionList);
        // when
        StoreResponse.UpdateMenuDTO respDTO = storeService.updateMenu(menuId, reqDTO);
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new App404("찾을 수 없는 메뉴입니다."));
        List<MenuOption> menuOptions = menuOptionRepository.findByMenuId(menuId)
                .orElseThrow(() -> new App404("찾을 수 없는 메뉴 옵션입니다."));
        // then
        Assertions.assertThat(respDTO.getPrice()).isEqualTo(10000);
        Assertions.assertThat(menu.getPrice()).isEqualTo(10000);
        Assertions.assertThat(respDTO.getMenuOptionList().size()).isEqualTo(3);
        Assertions.assertThat(menuOptions.size()).isEqualTo(3);
    }

    @Test // 매장 관리자 - 메뉴 삭제하기
    void deleteMenu_test() {
        // given
        int menuId = 1;
        int storeId = 1;
        // when
        storeService.deleteMenu(menuId);
        List<Menu> menuList = menuRepository.findAllByStoreId(storeId).get();
        // then
        Assertions.assertThat(menuList.size()).isEqualTo(4);
    }
    /*
    @Test // 매장 관리자 - 메뉴 옵션 등록하기
    void createMenuOption_test() {
        // given
        int menuId = 1;
        StoreRequest.CreateMenuOptionDTO reqDTO = new StoreRequest.CreateMenuOptionDTO();
        reqDTO.setName("testMenu");
        reqDTO.setRequired(true);
        reqDTO.setPrice(9999);
        // when
        StoreResponse.CreateMenuOptionDTO respDTO = storeService.createMenuOption(menuId, reqDTO);
        // then
        Assertions.assertThat(respDTO.getName()).isEqualTo("testMenu");
        Assertions.assertThat(respDTO.isRequired()).isEqualTo(true);
        Assertions.assertThat(respDTO.getPrice()).isEqualTo(9999);
    }

    @Test // 매장 관리자 - 메뉴 옵션 수정하기
    void updateMenuOption_test() {
        // given
        int optionId = 1;
        StoreRequest.UpdateMenuOptionDTO reqDTO = new StoreRequest.UpdateMenuOptionDTO();
        reqDTO.setName("testMenuOption");
        reqDTO.setPrice(999);
        reqDTO.setRequired(true);
        // when
        StoreResponse.UpdateMenuOptionDTO respDTO = storeService.updateMenuOption(optionId, reqDTO);
        // then
        Assertions.assertThat(respDTO.getName()).isEqualTo("testMenuOption");
        Assertions.assertThat(respDTO.getPrice()).isEqualTo(999);
        Assertions.assertThat(respDTO.isRequired()).isEqualTo(true);
    }

    @Test // 매장 관리자 - 메뉴 옵션 삭제하기
    void deleteMenuOption() {
        // given
        int menuOptionId = 1;
        int menuId = 1;
        // when
        storeService.deleteMenuOption(menuOptionId);
        List<MenuOption> menuOptionList = menuOptionRepository.findByMenuId(menuId).get();
        // then
        Assertions.assertThat(menuOptionList.size()).isEqualTo(5);
    }
    */
    @Test // 매장 관리자 - 주문 처리
    void updateOrder_test() {
        // given
        int orderId = 1;
        StoreRequest.UpdateOrderDTO reqDTO = new StoreRequest.UpdateOrderDTO();
        reqDTO.setStatus(OrderStatus.PREPARED); // 조리완료 -> 수령완료로 바뀜
        // when
        StoreResponse.UpdateOrderDTO respDTO = storeService.updateOrder(orderId, reqDTO);
        // then
        Assertions.assertThat(respDTO.getStatus()).isEqualTo(OrderStatus.SERVED);
    }

    @Test // 매장 관리자 - 주문내역 목록보기
    void getOrderList_test() {
        // given
        int storeId = 1;
        // when
        List<Order> orders = orderRepository.findAllByStoreId(storeId)
                .orElse(new ArrayList<>());
        StoreResponse.OrderListDTO respDTO = storeService.getOrderList(storeId);
        // then
        Assertions.assertThat(orders.size()).isEqualTo(12);
        Assertions.assertThat(respDTO.getOrderList().size()).isEqualTo(7); // 수령완료가 된 것만 응답된다.

    }

    @Test // 매장 관리자 - 주문내역 상세보기
    void getOrderDetail_test() { // Transaction(readOnly = true) 필수. Or Lazy Initialization Exception
        // given
        int orderId = 1;
        // when
        StoreResponse.OrderDetailDTO respDTO = storeService.getOrderDetail(orderId);
        // then
        Assertions.assertThat(respDTO.getOrderMenuList().size()).isEqualTo(5);
    }
}