package shop.project.pathorderserver.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderMenuRepositoryTest {
    @Autowired
    OrderMenuRepository orderMenuRepository;

    @Test
    public void findOrderOptionByOrderId_test() {
        //given
        int orderMenuId = 1;

        // when
        Optional<List<OrderOption>> orderOptionOP = orderMenuRepository.findOrderOptionByOrderId(orderMenuId);
        // then
        System.out.println(orderOptionOP);
    }
}