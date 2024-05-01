package shop.project.pathorderserver.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreServiceTest {
    @Autowired
    StoreService storeService;

    @Test
    void createStore_test() {
        // given
        StoreRequest.JoinDTO reqDTO = new StoreRequest.JoinDTO();
        reqDTO.setUsername("유저네임");
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
        Assertions.assertThat(respDTO.getOwnerName()).isEqualTo("성재");
    }

    @Test
    void getStore_test() {
        // given
        StoreRequest.LoginDTO reqDTO = new StoreRequest.LoginDTO();
        reqDTO.setUsername("jake1234");
        reqDTO.setPassword("1234");
        // when
        SessionStore sessionStore = storeService.getStore(reqDTO);
        // then
        Assertions.assertThat(sessionStore.getUsername()).isEqualTo("jake1");
    }

    @Test
    void getOrderList_test() {
        // given
        int storeId = 1;
        // when
        StoreResponse.OrderListDTO respDTO = storeService.getOrderList(storeId);
        // then
        Assertions.assertThat(respDTO.getOrderList().size()).isEqualTo(1);
    }

    @Test // Transaction(readOnly = true) 안하면 터짐. Lazy Initialization Exception
    void getOrderDetail_test() {
        // given
        int orderId = 1;
        // when
        StoreResponse.OrderDetailDTO respDTO = storeService.getOrderDetail(orderId);
        System.out.println(respDTO.getOrderMenuList().size());
        // then
        Assertions.assertThat(respDTO.getOrderMenuList().size()).isEqualTo(3);
    }

    @Test
    void getOwnerMenuList_test() {
        // given
        int storeId = 1;
        // when
        StoreResponse.OwnerMenuListDTO respDTO = storeService.getOwnerMenuList(storeId);
        // then
        Assertions.assertThat(respDTO.getMenuList().size()).isEqualTo(5);
    }
}