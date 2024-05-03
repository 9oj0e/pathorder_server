package shop.project.pathorderserver.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver.order.OrderStatus;

@SpringBootTest
@Transactional
class StoreServiceTest {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;

    @Test // 점주 회원가입
    void createStore_test() {
        // given
        StoreRequest.JoinDTO reqDTO = new StoreRequest.JoinDTO();
        reqDTO.setUsername("아이디");
        reqDTO.setPassword("1234");
        reqDTO.setOwnerName("김성재");
        reqDTO.setOwnerTel("전화번호");
        reqDTO.setOwnerEmail("이메일");
        reqDTO.setBizNum("사업자번호");
        reqDTO.setName("매장이름");
        reqDTO.setTel("매장번호");
        reqDTO.setIntro("매장소개");
        reqDTO.setOpeningTime("개점시간");
        reqDTO.setClosingTime("폐점시간");
        reqDTO.setClosedDay("휴무일");
        reqDTO.setAddress("주소");
        // when
        StoreResponse.JoinDTO respDTO = storeService.createStore(reqDTO);
        // then
        Assertions.assertThat(respDTO.getOwnerName()).isEqualTo("김성재");
    }

    @Test // 점주 로그인
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

    @Test // 메뉴 등록
    void createMenu_Test() {
        // given
        int storeId = 1;
        StoreRequest.CreateMenuDTO reqDTO = new StoreRequest.CreateMenuDTO();
        reqDTO.setPrice(3000);
        reqDTO.setCategory("과일 음료");
        reqDTO.setName("자몽에이드");
        reqDTO.setDescription("내가 제일 좋아함");
        // when
        StoreResponse.CreateMenuDTO respDTO = storeService.createMenu(storeId, reqDTO);
        // then
        Assertions.assertThat(respDTO.getName()).isEqualTo("자몽에이드");
    }

    @Test
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

    @Test // 점주 주문내역 목록보기
    void getOrderList_test() {
        // given
        int storeId = 1;
        // when
        StoreResponse.OrderListDTO respDTO = storeService.getOrderList(storeId);
        // then
        Assertions.assertThat(respDTO.getOrderList().size()).isEqualTo(5);
    }

    @Test // 점주 주문내역 상세보기
    void getOrderDetail_test() { // Transaction(readOnly = true) 필수. Or Lazy Initialization Exception
        // given
        int orderId = 1;
        // when
        StoreResponse.OrderDetailDTO respDTO = storeService.getOrderDetail(orderId);
        // then
        Assertions.assertThat(respDTO.getOrderMenuList().size()).isEqualTo(3);
    }

    @Test // 점주 메뉴 목록보기
    void getMenuList_test() {
        // given
        int storeId = 1;
        // when
        StoreResponse.MenuListDTO respDTO = storeService.getMenuList(storeId);
        // then
        Assertions.assertThat(respDTO.getMenuList().size()).isEqualTo(5);
    }

    @Test // 점주 매장 정보 상세보기
    void getStoreDetail() {
        // given
        int storeId = 1;
        // when
        StoreResponse.StoreDTO respDTO = storeService.getStoreDetail(storeId);
        // then
        Assertions.assertThat(respDTO.getName()).isEqualTo("단밤 카페");
    }

    @Test // 매장정보 수정하기
    void updateStore_test() {
        // given
        int sessionId = 1;
        StoreRequest.UpdateDTO reqDTO = new StoreRequest.UpdateDTO();
        reqDTO.setUsername("1234");
        // when
        // 변경 전 확인
        // Store store = storeRepository.findById(sessionId).get();
        // System.out.println("_test: " + store.getUsername());
        SessionStore sessionStore = storeService.updateStore(sessionId, reqDTO);
        // then
        Assertions.assertThat(sessionStore.getUsername()).isEqualTo("1234");
    }

    @Test // 주문 처리
    void updateOrder_test() {
        // given
        int orderId = 1;
        StoreRequest.UpdateOrderDTO reqDTO = new StoreRequest.UpdateOrderDTO();
        reqDTO.setStatus(OrderStatus.조리완료);
        // when
        StoreResponse.UpdateOrderDTO respDTO = storeService.updateOrder(orderId, reqDTO);
        // then
        Assertions.assertThat(respDTO.getStatus()).isEqualTo(OrderStatus.조리완료);
    }

    @Test
    void updateMenu_test() {
        // given
        int menuId = 1;
        StoreRequest.UpdateMenuDTO reqDTO = new StoreRequest.UpdateMenuDTO();
        reqDTO.setPrice(10000);
        // when
        StoreResponse.UpdateMenuDTO respDTO = storeService.updateMenu(menuId, reqDTO);
        // then
        Assertions.assertThat(respDTO.getPrice()).isEqualTo(10000);
    }
}