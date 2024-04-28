package shop.project.pathorderserver.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // 한 유저의 주문내역
    @Query("select o from Order o where o.customer.id = :userId")
    Optional<List<Order>> findByUserId(@Param("userId") Integer userId);
}
