package shop.project.pathorderserver.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.project.pathorderserver._core.errors.exception.Exception404;

import java.util.List;

@DataJpaTest
class OrderMenuRepositoryTest {
    @Autowired
    OrderMenuRepository orderMenuRepository;

    @Test // 주문 메뉴 목록보기
    void findAllByOrderId_test() {
        // given
        int orderId = 1;
        // when
        List<OrderMenu> orderMenus = orderMenuRepository.findAllByOrderId(orderId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문 메뉴 리스트."));
        // then
        Assertions.assertThat(orderMenus.size()).isEqualTo(3);
    }
}