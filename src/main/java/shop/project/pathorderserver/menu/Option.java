package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "option_tb")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu; // 하나의 메뉴는 여러개의 옵션을 참조
    private String name;
    private int price;
    private boolean isRequired; // 필수 옵션 여부
    @CreationTimestamp
    private Timestamp createdAt;
}
