package shop.project.pathorderserver.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer> {
    Optional<List<OrderMenu>> findAllByOrderId(@Param("orderId")int orderId);

    @Query("SELECT o FROM OrderOption o WHERE o.orderMenu.id = :orderMenuId")
    Optional<List<OrderOption>> findAllOptionByMenuId(@Param("orderMenuId")int orderMenuId);
}
