package shop.project.pathorderserver.review;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.user.User;

import java.sql.Timestamp;

@Entity
@Table(name = "review_tb")
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
    private String usersName;
    private String storesName;
    private String content;
    private String imgFilename;
    @CreationTimestamp
    private Timestamp createdAt;

    public Review(ReviewRequest.AddDTO reqDTO, User user, Store store) {
        this.user = user;
        this.store = store;
        this.usersName = user.getName();
        this.storesName = store.getName();
        this.content = reqDTO.getContent();
        this.imgFilename = reqDTO.getImgFilename();
    }
}
