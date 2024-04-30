package shop.project.pathorderserver.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreServiceTest {
    @Autowired
    StoreService storeService;

    @Test
    void 매장등록_test() {
        // given
        StoreRequest.매장등록 reqDTO = new StoreRequest.매장등록();
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
        Store store = storeService.매장등록(reqDTO);
        // then
        Assertions.assertThat(store.getOwnerName()).isEqualTo("성재");
    }
}