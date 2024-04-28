package shop.project.pathorderserver.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {


    @Query("""
            select om
            from OrderMenu om
            join om.order o
            where o.id = :orderId
            """)
    Optional<List<OrderMenu>> findOrderMenuByOrderId(@Param("orderId") Integer orderId);

//    @Query("""
//            select op
//            from OrderOption op
//            join op.order o
//            join op.orderMenu om on om.order.id = op.orderMenu.order.id
//            where o.id = :orderId
//            """)
//    Optional<List<OrderOption>> findOrderOptionByOrderId(@Param("orderId") Integer orderId);
}
