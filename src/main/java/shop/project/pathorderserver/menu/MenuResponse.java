package shop.project.pathorderserver.menu;

import lombok.Data;

public class MenuResponse {

    // 매장 메뉴 DTO
    @Data
    public static class StoreMenuDTO {
        private String name;
        private String description;
        private int price;
        private String imgSrc;

        public StoreMenuDTO(Menu menu) {
            this.menuName = menu.getName();
            this.description = menu.getDescription();
            this.price = menu.getPrice();
            this.imgSrc = menu.getImgSrc();
        }
    }
}
