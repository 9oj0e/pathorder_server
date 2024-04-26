package shop.project.pathorderserver.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StoreServiceTest {
    @Autowired
    private StoreRepository storeRepository;

    @Test
    void findAll_test() {
        Optional<List<Store>> listings = Optional.of(storeRepository.findAll());
        Assertions.assertThat(listings.get().size()).isEqualTo(5);
        Assertions.assertThat(listings.get().getFirst().getName()).isEqualTo("단밤 카페");
    }
}