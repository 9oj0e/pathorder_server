package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

public class MenuResponse {

    // 매장 메뉴 DTO
    @Data
    public static class StoreMenuDTO {
        private String name;
        private String description;
        private int price;
        private String imgFilename;

        public StoreMenuDTO(Menu menu) {
            this.name = menu.getName();
            this.description = menu.getDescription();
            this.price = menu.getPrice();
            this.imgFilename = menu.getImgFilename();
        }
    }

    // 메뉴별 옵션 DTO
    @Data
    public static class OptionDTO {
        // 메뉴
        // 옵션
        private int optionId;
        private String optionName;
        private int optionPrice;
        private boolean isRequired;

        public OptionDTO(Option option) {
            this.optionId = option.getId();
            this.optionName = option.getName();
            this.optionPrice = option.getPrice();
            this.isRequired = option.isRequired();
        }
    }
}
