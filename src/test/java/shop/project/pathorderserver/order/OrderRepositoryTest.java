package shop.project.pathorderserver.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    void findByUserId_test() {
        // given
        Integer userId = 1;
        // when
        orderRepository.findByUserId(userId);
    }

    @Test
    void findByStoreId_test() {
        // given
        Integer storeId = 1;
        // when
        Optional<List<Order>> orderList = orderRepository.findByStoreId(storeId);
        // then
        Assertions.assertThat(orderList.get().size()).isEqualTo(1);
    }
}