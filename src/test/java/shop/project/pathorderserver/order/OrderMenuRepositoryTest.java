package shop.project.pathorderserver.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderMenuRepositoryTest {
    @Autowired
    OrderMenuRepository orderMenuRepository;

    @Test
    void findMenusById() {
        int orderId = 1;
        orderMenuRepository.findMenusById(orderId);
    }
}