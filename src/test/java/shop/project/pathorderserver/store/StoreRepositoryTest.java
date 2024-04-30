package shop.project.pathorderserver.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;

    @Test
    void findAll_test() {
        Optional<List<Store>> storeList = Optional.of(storeRepository.findAll());
        Assertions.assertThat(storeList.get().size()).isEqualTo(5);
        Assertions.assertThat(storeList.get().getFirst().getName()).isEqualTo("단밤 카페");
    }

    @Test
    public void findById_test() {
        int storeId = 1;
        Optional<Store> storeOP = storeRepository.findById(storeId);
        Assertions.assertThat(storeOP.get().getName()).isEqualTo("단밤 카페");
        Assertions.assertThat(storeOP.get().getUsername()).isEqualTo("조정현");
    }

    @Test
    public void findByUsernameAndPassword_test() {
        //given
        String username = "jake1234";
        String password = "1234";
        //when
        Optional<Store> storeOP = storeRepository.findByUsernameAndPassword(username, password);

        //then
        System.out.println("findByUsernameAndPassword_test:" + storeOP);

    }
}