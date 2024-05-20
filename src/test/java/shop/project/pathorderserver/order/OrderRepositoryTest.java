package shop.project.pathorderserver.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.project.pathorderserver._core.errors.exception.Exception404;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        assertThat(orders.size()).isEqualTo(2);
    }

    @Test
        // 매장 주문내역 목록보기
    void findAllByStoreId_test() {
        // given
        int storeId = 1;
        // when
        Optional<List<Order>> orderList = orderRepository.findAllByStoreId(storeId);
        // then
        assertThat(orderList.get().size()).isEqualTo(12);
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
        assertThat(orderList.getFirst().getStatus()).isEqualTo(OrderStatus.SERVED);

    }

    @Test
    public void findAllByStoreIdAndCreatedAtBetween_test() {
        //given
        int storeId = 1;
        LocalDate startDate = LocalDate.of(2024, 1, 21);
        LocalDate endDate = LocalDate.of(2024, 5, 20);

        // when
        List<Order> orderList = orderRepository.findAllByStoreIdAndCreatedAtBetween(storeId, startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
        // then
        Assertions.assertThat(orderList.getFirst().getOrderMenus().getFirst().getName()).isEqualTo("아메리카노");
        Assertions.assertThat(orderList.getFirst().getCustomerNickname()).isEqualTo("찬혁");
    }
}
