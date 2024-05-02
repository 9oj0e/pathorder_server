package shop.project.pathorderserver.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreServiceTest {
    @Autowired
    StoreService storeService;

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
    void getOwnerMenuList_test() {
        // given
        int storeId = 1;
        // when
        StoreResponse.OwnerMenuListDTO respDTO = storeService.getMenuList(storeId);
        // then
        Assertions.assertThat(respDTO.getMenuList().size()).isEqualTo(5);
    }
}