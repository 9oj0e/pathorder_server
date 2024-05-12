package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;
import shop.project.pathorderserver._core.DefaultFile;
import shop.project.pathorderserver._core.utils.FileUtil;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRequest;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
@DynamicInsert
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
    @ColumnDefault("'default/beverage.png'")
    private String imgFilename;
    private String description; // 메뉴 설명
    // 참조 정보 - 매장
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store; // 하나의 매장은 여러 개의 메뉴를 가질 수 있음

    @OneToMany(fetch = FetchType.EAGER)
    private List<MenuOption> menuOptions;

    @CreationTimestamp
    private Timestamp registeredAt; // 메뉴 등록일

    public Menu(StoreRequest.CreateMenuDTO reqDTO, Store store) {
        this.price = reqDTO.getPrice();
        this.category = reqDTO.getCategory();
        this.name = reqDTO.getName();
        setImgFilename(reqDTO.getImgFile());
        this.description = reqDTO.getDescription();
        this.store = store;
    }

    public void update(StoreRequest.UpdateMenuDTO reqDTO) {
        setPrice(reqDTO.getPrice());
        setCategory(reqDTO.getCategory());
        setName(reqDTO.getName());
        setImgFilename(reqDTO.getEncodedFile());
        setDescription(reqDTO.getDescription());
    }

    private void setImgFilename(String encodedFile) {
        FileUtil.deleteFile(this.getImgFilename());
        String imgFilename = FileUtil.uploadBase64(encodedFile, this.name);
        if (imgFilename.equals("default")) { // TODO: 삭제 로직 분리하기, 사진 유지 추가
            this.imgFilename = DefaultFile.BEVERAGE.getPath();
        } else {
            this.imgFilename = imgFilename;
        }
    }

    private void setImgFilename(MultipartFile imgFile) {
        FileUtil.deleteFile(this.getImgFilename());
        boolean hasNoImg = imgFile == null || imgFile.isEmpty();
        if (!hasNoImg) {
            this.imgFilename = FileUtil.uploadFile(imgFile);
        } else {
            this.imgFilename = DefaultFile.BEVERAGE.getPath();
        }
    }
}
