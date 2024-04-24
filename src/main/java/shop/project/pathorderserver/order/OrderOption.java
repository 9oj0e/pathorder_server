package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "order_option_tb")
public class OrderOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne (fetch = FetchType.LAZY)
    private OrderMenu orderMenu; // 하나의 메뉴는 여러 옵션을 가질 수 있음
    private String name; // 옵션의 이름
    private int price; // 옵션 하나의 가격
    @CreationTimestamp
    private Timestamp createdAt;
}
