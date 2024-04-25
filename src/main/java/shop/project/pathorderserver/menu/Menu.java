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
    private Store store; // 하나의 매장은 여러 개의 메뉴를 가질 수 있음
    private String category; // 각 메뉴가 포함되는 카테고리, 점주가 직접 작성
    private String name; // 메뉴 이름
    private int price; // 메뉴 하나의 가격
    private String imgSrc;
    private String description; // 메뉴 설명
    @CreationTimestamp
    private Timestamp registeredAt; // 메뉴 등록일
}
