package shop.project.pathorderserver.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderMenuOptionRepository extends JpaRepository<OrderMenuOption, Integer> {
    @Query("SELECT o FROM OrderMenuOption o WHERE o.orderMenu.id = :orderMenuId")
    Optional<List<OrderMenuOption>> findAllByMenuId(@Param("orderMenuId")int orderMenuId);
}
