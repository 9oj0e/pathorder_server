package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRequest;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "menu_tb")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 메뉴 정보
    private int price; // 메뉴 하나의 가격
    private String category; // 각 메뉴가 포함되는 카테고리, 점주가 직접 작성
    private String name; // 메뉴 이름
    private String imgFilename;
    private String description; // 메뉴 설명
    // 참조 정보 - 매장
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store; // 하나의 매장은 여러 개의 메뉴를 가질 수 있음

    @CreationTimestamp
    private Timestamp registeredAt; // 메뉴 등록일

    public Menu(StoreRequest.CreateMenuDTO reqDTO, Store store) {
        this.price = price;
        this.category = category;
        this.name = name;
        this.imgFilename = imgFilename;
        this.description = description;
        this.store = store;
    }
}
