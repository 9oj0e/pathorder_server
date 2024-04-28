package shop.project.pathorderserver.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void findByIdWithMenu_test() {
        //given
        int orderId = 1;

        // when
        Optional<List<OrderMenu>> orderMenuOP = orderRepository.findOrderMenuByOrderId(orderId);
        // then
        System.out.println(orderMenuOP);
    }

//    @Test
//    public void findOrderOptionByOrderId_test() {
//        //given
//        int orderId = 1;
//
//        // when
//        Optional<List<OrderOption>> orderOptionOP = orderRepository.findOrderOptionByOrderId(orderId);
//        // then
//        System.out.println(orderOptionOP);
//    }
}