package shop.project.pathorderserver.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.customer.id = :userId ORDER BY o.id DESC") // 주문내역 목록보기 (회원)
    Optional<List<Order>> findByUserId(@Param("userId") Integer userId);

    @Query("select o from Order o where o.store.id = :storeId order by o.id desc")
    Optional<List<Order>> findByStoreId(@Param("storeId") Integer storeId); // 주문내역 목록보기 (점주)
}
