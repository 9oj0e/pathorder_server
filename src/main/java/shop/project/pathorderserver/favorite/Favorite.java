package shop.project.pathorderserver.favorite;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import shop.project.pathorderserver.order.OrderMenu;
import shop.project.pathorderserver.order.OrderStatus;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.user.User;
import shop.project.pathorderserver.user.UserRequest;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "favorite_tb")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 손님 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    @CreationTimestamp
    private Timestamp createdAt; // 좋아요 한 시간
}

