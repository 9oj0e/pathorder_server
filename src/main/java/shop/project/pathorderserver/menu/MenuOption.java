package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "menu_option_tb")
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 메뉴 옵션 정보
    private int price;
    private String name;
    private boolean isRequired; // 필수 옵션 여부
    // 참조 정보 - 메뉴
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu; // 하나의 메뉴는 여러개의 옵션을 참조
    // @CreationTimestamp
    // private Timestamp createdAt;
}
