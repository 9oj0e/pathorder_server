package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.user.User;
import shop.project.pathorderserver.user.UserRequest;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
@DynamicInsert
@Entity
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 손님 정보
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer; // 한 명의 손님은 여러 건의 주문을 할 수 있음
    private String customerNickname; // 주문자 닉네임 (주문 번호 생성 로직이 복잡함..)
    @ColumnDefault("'요청 사항이 없습니다.'")
    private String request; // 요청 사항, ex) 연하게 해주세요, 캐리어에 담아주세요, 얼음 많이 넣어주세요.
    // 매장 정보
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store; // 하나의 매장은 여러 건의 주문을 받을 수 있음
    private String storeName; // 매장이름은 가져오기
    // 주문 정보
    private int totalPrice = 0; // 총 가격
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PENDING'")
    private OrderStatus status; // 조리 상태(0 : 조리중, 1 : 조리완료)
    // 주문 메뉴 정보
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderMenu> orderMenus;

    @CreationTimestamp
    private Timestamp createdAt; // 주문 시간
    // private LocalDateTime 수령시각;

    public Order(UserRequest.OrderDTO reqDTO, User customer, Store store) {
        this.customer = customer;
        this.store = store;
        this.storeName = reqDTO.getStoreName();
        this.customerNickname = reqDTO.getCustomerNickname();
        this.request = reqDTO.getRequest();
        this.status = (reqDTO.getStatus() != null) ? reqDTO.getStatus() : OrderStatus.PENDING;
    }

    public void updateTotalPrice() {
        for (OrderMenu orderMenu : this.orderMenus) {
            this.totalPrice += orderMenu.getTotalPrice();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order{")
                .append("id=").append(id)
                .append(", createdAt=").append(createdAt)
                .append(", customerNickname='").append(customerNickname).append('\'')
                .append(", request='").append(request).append('\'')
                .append(", storeName='").append(storeName).append('\'')
                .append(", totalPrice=").append(totalPrice)
                .append(", status=").append(status);

        if (orderMenus != null && !orderMenus.isEmpty()) {
            stringBuilder.append(", orderMenus=[");
            for (OrderMenu orderMenu : orderMenus) {
                stringBuilder.append(orderMenu.toStringWithoutOrder()).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("]");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
