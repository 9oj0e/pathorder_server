package shop.project.pathorderserver.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.project.pathorderserver._core.errors.exception.Exception404;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test // 손님 주문내역 목록보기
    void findByUserId_test() {
        // given
        Integer userId = 1;
        // when
        List<Order> orders = orderRepository.findByUserId(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));
        // then
        Assertions.assertThat(orders.size()).isEqualTo(1);
    }

    @Test // 매장 주문내역 목록보기
    void findByStoreId_test() {
        // given
        Integer storeId = 1;
        // when
        Optional<List<Order>> orderList = orderRepository.findByStoreId(storeId);
        // then
        Assertions.assertThat(orderList.get().size()).isEqualTo(5);
    }
}