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
    private int totalPrice = 0; // 옵션을 더한 가격
    private int qty; // 개수
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
        this.qty = reqDTO.getQty();
    }

    public void updateTotalPrice() {
        int optionTotalPrice = 0;
        for (OrderMenuOption orderMenuOption : orderMenuOptions) {
            optionTotalPrice += orderMenuOption.getPrice();
        }
        this.totalPrice = (price + optionTotalPrice) * qty;
    }

    public String toStringWithoutOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OrderMenu{")
                .append("name='").append(name).append('\'')
                .append(", price=").append(price)
                .append(", qty=").append(qty);

        if (orderMenuOptions != null && !orderMenuOptions.isEmpty()) {
            stringBuilder.append(", orderMenuOptions=[");
            for (OrderMenuOption orderMenuOption : orderMenuOptions) {
                stringBuilder.append(orderMenuOption.toStringWithoutOrderMenu()).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("]");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
