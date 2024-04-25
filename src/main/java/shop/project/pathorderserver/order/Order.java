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
    private Store store; // 하나의 매장은 여러 건의 주문을 받을 수 있음
    private String storeName; // 매장이름은 가져오기
    // 손님 정보
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer; // 한 명의 손님은 여러 건의 주문을 할 수 있음
    private String customerName; // 주문자 닉네임 (주문 번호 생성 로직이 복잡함..)
    private String request; // 요청 사항, ex) 연하게 해주세요, 캐리어에 담아주세요, 얼음 많이 넣어주세요.
    // 주문 정보
    private int totalAmount; // 총 가격
    // @ColumnDefault("접수 대기")
    private boolean status; // enum ("접수 대기", "주문 거절", "주문 완료", "조리 중", "조리 완료")
    @CreationTimestamp
    private Timestamp createdAt; // 주문 시간
}
