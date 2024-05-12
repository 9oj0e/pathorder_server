package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.project.pathorderserver.store.StoreRequest;

@NoArgsConstructor
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

    public MenuOption(StoreRequest.UpdateMenuDTO.MenuOptionDTO reqDTO, Menu menu) {
        this.price = reqDTO.getPrice();
        this.name = reqDTO.getName();
        this.isRequired = reqDTO.isRequired();
        this.menu = menu;
    }


    /*
    public MenuOption(StoreRequest.CreateMenuOptionDTO reqDTO, Menu menu) {
        this.price = reqDTO.getPrice();
        this.name = reqDTO.getName();
        this.isRequired = reqDTO.isRequired();
        this.menu = menu;
    }

    public void update(StoreRequest.UpdateMenuOptionDTO reqDTO) {
        this.price = reqDTO.getPrice();
        this.name = reqDTO.getName();
        this.isRequired = reqDTO.isRequired();
        this.menu = menu;
    }
    */
}
