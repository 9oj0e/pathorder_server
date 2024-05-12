package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.project.pathorderserver.user.UserRequest;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "order_menu_option_tb")
public class OrderMenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 주문 메뉴 옵션
    private int price; // 옵션 하나의 가격
    private String name; // 옵션의 이름
    // 참조 정보
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order; // 하나의 주문은 여러 메뉴 옵션을 가질 수 있음
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderMenu orderMenu; // 하나의 메뉴는 여러 옵션을 가질 수 있음

    @CreationTimestamp
    private Timestamp createdAt;

    public OrderMenuOption(UserRequest.OrderDTO.OrderMenuOptionDTO reqDTO, Order order, OrderMenu orderMenu) {
        this.order = order;
        this.orderMenu = orderMenu;
        this.name = reqDTO.getName();
        this.price = reqDTO.getPrice();
    }

    public String toStringWithoutOrderMenu() {
        return "OrderMenuOption{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
