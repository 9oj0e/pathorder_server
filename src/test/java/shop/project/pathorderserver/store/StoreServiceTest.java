package shop.project.pathorderserver.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StoreServiceTest {
    @Autowired
    private StoreRepository storeRepository;

    @Test
    void findAll_test() {
        Optional<List<Store>> listingsOP = Optional.of(storeRepository.findAll());
        assertThat(listingsOP.get().size()).isEqualTo(5);
        assertThat(listingsOP.get().getFirst().getName()).isEqualTo("단밤 카페");
    }

    @Test
    public void findById_test() {
        int storeId = 1;
        Optional<Store> storeOP = storeRepository.findById(storeId);
        assertThat(storeOP.get().getName()).isEqualTo("단밤 카페");
        assertThat(storeOP.get().getUsername()).isEqualTo("조정현");
    }
}