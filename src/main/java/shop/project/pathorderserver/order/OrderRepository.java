package shop.project.pathorderserver.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.customer.id = :userId ORDER BY o.id DESC")
        // 주문내역 목록보기 (회원)
    Optional<List<Order>> findAllByUserId(@Param("userId") int userId);

    @Query("SELECT o FROM Order o WHERE o.store.id = :storeId ORDER BY o.id desc")
    Optional<List<Order>> findAllByStoreId(@Param("storeId") int storeId); // 주문내역 목록보기 (점주)

    @Query("""
            SELECT o 
            FROM Order o
            JOIN FETCH o.store s
            LEFT JOIN FETCH o.orderMenus om
            WHERE s.id = :storeId
            """) // 주문보기 (점주)
    Optional<List<Order>> findAllByStoreIdWithOrderMenu(int storeId);
}
