package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.project.pathorderserver.store.Store;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "menu_tb")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store; // 하나의 매장은 여러개의 메뉴를 참조
    private String category; // 분류
    private String name;
    private int price;
    private String imgSrc;
    private String description; // 설명
    private Timestamp releasedAt; // 출시일
    @CreationTimestamp
    private Timestamp registeredAt; // 등록일
}
