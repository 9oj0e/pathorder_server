package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
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
        this.description = reqDTO.getDescription();
        this.store = store;

        boolean hasNoImg = reqDTO.getImgFile() == null || reqDTO.getImgFile().isEmpty();
        if (!hasNoImg) {
            this.imgFilename = FileUtil.fileUpload(reqDTO.getImgFile());
        }
    }

    public void update(StoreRequest.UpdateMenuDTO reqDTO) {
        setPrice(reqDTO.getPrice());
        setCategory(reqDTO.getCategory());
        setName(reqDTO.getName());

        boolean hasNoImg = reqDTO.getEncodedFile() == null || reqDTO.getEncodedFile().isEmpty();
        if (!hasNoImg) {
            setImgFilename(
                    FileUtil.base64Upload(reqDTO.getEncodedFile(), reqDTO.getName(), "jpg")
            );
        }
        setDescription(reqDTO.getDescription());
    }
}
