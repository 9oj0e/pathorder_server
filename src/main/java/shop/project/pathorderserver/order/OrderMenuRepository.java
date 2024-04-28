package shop.project.pathorderserver.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer> {
    @Query("""
            select op
            from OrderOption op
            join op.order o
            join op.orderMenu om on om.order.id = op.orderMenu.order.id
            where om.id = :orderMenuId
            """)
    Optional<List<OrderOption>> findOrderOptionByOrderId(@Param("orderMenuId") Integer orderMenuId);
}
