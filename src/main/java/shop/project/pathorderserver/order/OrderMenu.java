package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_menu_tb")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order; // 하나의 주문은 여러 메뉴를 가질 수 있음
    private String name; // 메뉴의 이름
    @OneToMany(mappedBy = "orderMenu")
    private List<OrderMenuOption> orderMenuOption = new ArrayList<>();
    private int price; // 메뉴 하나의 가격
    @CreationTimestamp
    private Timestamp createdAt;
}
