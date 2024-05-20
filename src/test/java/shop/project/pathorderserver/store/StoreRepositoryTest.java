package shop.project.pathorderserver.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.project.pathorderserver._core.errors.exception.App404;

import java.util.List;

@DataJpaTest
class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;

    @Test // 매장 목록보기
    void findAll_test() {
        // when
        List<Store> stores = storeRepository.findAll();
        // then
        Assertions.assertThat(stores.size()).isEqualTo(6);
        Assertions.assertThat(stores.getFirst().getName()).isEqualTo("연의양과");
    }

    @Test // 매장 정보보기
    public void findById_test() {
        // given
        int storeId = 1;
        // when
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new App404("찾을 수 없는 매장"));
        // then
        Assertions.assertThat(store.getName()).isEqualTo("연의양과");
        Assertions.assertThat(store.getOwnerName()).isEqualTo("조정현");
    }

    @Test
    public void findByUsernameAndPassword_test() {
        //given
        String username = "jake1234";
        String password = "1234";
        //when
        Store store = storeRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new App404("찾을 수 없는 계정"));
        //then
        Assertions.assertThat(store.getUsername()).isEqualTo("jake1234");
    }
}