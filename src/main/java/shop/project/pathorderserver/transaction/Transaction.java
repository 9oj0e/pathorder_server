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
    private Order order; // 하나의 결제는 하나의 주문
    private String method; // 결제 방식
    private int amount; // 결제 금액
    private boolean status; // 결제 상태
    @CreationTimestamp
    private Timestamp createdAt; // 생성일
}
