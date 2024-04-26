package shop.project.pathorderserver.store;

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
        System.out.println("findAll_test: " + listings);

    }
}