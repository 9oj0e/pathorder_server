package shop.project.pathorderserver.review;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.project.pathorderserver._core.utils.FileUtil;
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
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Store store;
    private String content;
    private String imgFilename;
    @CreationTimestamp
    private Timestamp createdAt;

    public Review(ReviewRequest.AddDTO reqDTO, User user, Store store) {
        this.user = user;
        this.store = store;
        this.content = reqDTO.getContent();
        setImgFilename(reqDTO.getEncodedData());
    }

    public void setImgFilename(String encodedFile) {
        String imgFilename = FileUtil.uploadBase64Jpg(encodedFile, "review");
        if (imgFilename.equals("default")) {
            this.imgFilename = null;
        } else {
            this.imgFilename = imgFilename;
        }
    }
}
