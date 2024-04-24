package shop.project.pathorderserver.transaction;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.project.pathorderserver.order.Order;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "transaction_tb")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Order order; // 하나의 결제는 하나의 주문과 연결된다.
    // TODO: 결제 방식은 카드결제 정도로만 할 예정이어서 이것을 필드에 포함시킬지에 대한 논의가 필요하다고 생각됨. 카드 결제만 될 경우에도 결제 테이블에는 포함시켜야 할 것 같기는 함.
    private String method; // 결제 방식(카드 결제)
    private int amount; // 결제 금액
    // TODO:
    private boolean status; // 결제 상태(0: 결제 실패, 1: 결제 성공)
    @CreationTimestamp
    private Timestamp createdAt; // 생성일
}
