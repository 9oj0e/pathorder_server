package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "order_menu")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order; // 하나의 주문은 여러 메뉴를 가진다.
    private String name;
    private int qty;
    private int price;
    @CreationTimestamp
    private Timestamp createdAt;
}
