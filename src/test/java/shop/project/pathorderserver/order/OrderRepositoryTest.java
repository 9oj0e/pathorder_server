package shop.project.pathorderserver.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.project.pathorderserver._core.errors.exception.Exception404;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
        // 손님 주문내역 목록보기
    void findAllByUserId_test() {
        // given
        int userId = 1;
        // when
        List<Order> orders = orderRepository.findAllByUserId(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));
        // then
        assertThat(orders.size()).isEqualTo(1);
    }

    @Test
        // 매장 주문내역 목록보기
    void findAllByStoreId_test() {
        // given
        int storeId = 1;
        // when
        Optional<List<Order>> orderList = orderRepository.findAllByStoreId(storeId);
        // then
        assertThat(orderList.get().size()).isEqualTo(5);
    }

    @Test
    public void findAllByStoreIdWithOrderMenu_test() {
        //given
        int storeId = 1;
        // when
        List<Order> orderList = orderRepository.findAllByStoreIdWithOrderMenu(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));
        // then
//        System.out.println("findOrdersByStoreId_test: " + orderListOP);
        assertThat(orderList.getFirst().getStore().getUsername()).isEqualTo("david1234");
        assertThat(orderList.getFirst().getStatus()).isEqualTo(OrderStatus.조리중);

    }
}
