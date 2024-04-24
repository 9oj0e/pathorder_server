package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.user.User;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // 매장 정보
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store; // 하나의 매장은 여러개의 주문을 참조
    private String storeName; // 매장이름은 가져오기
    // 손님 정보
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer; // 하나의 손님은 어려개의 주문을 참조
    private String customerName; // 주문자 닉네임 (주문 번호 생성 로직이 복잡함..)
    private String request; // 요청 사항
    // 주문 정보
    private int totalAmount; // 총 가격
    private boolean isAccepted; // 주문 수락 여부
    private boolean status; // 주문 상태
    @CreationTimestamp
    private Timestamp createdAt; // 주문 시간
}
