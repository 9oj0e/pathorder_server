package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.project.pathorderserver.user.UserRequest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "order_menu_tb")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 메뉴 정보
    private String name; // 메뉴의 이름
    private int price; // 메뉴 하나의 가격
    // 참조 정보
    // private int menuId; // 참조할 메뉴의 아이디 (reqDTO, db, service 수정 필요)
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order; // 하나의 주문은 여러 메뉴를 가질 수 있음
    @OneToMany(mappedBy = "orderMenu")
    private List<OrderMenuOption> orderMenuOptions = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    public OrderMenu(UserRequest.OrderDTO.OrderMenuDTO reqDTO, Order order) {
        this.order = order;
        // this.menuId = reqDTO.getMenuId();
        this.name = reqDTO.getName();
        this.price = reqDTO.getPrice();
    }
}
