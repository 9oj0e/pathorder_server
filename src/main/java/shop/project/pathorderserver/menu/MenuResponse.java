package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;
import shop.project.pathorderserver.store.Store;

import java.util.ArrayList;
import java.util.List;

public class MenuResponse {

    // 매장 메뉴 DTO
    @Data
    public static class StoreMenuDTO {
        private int storeId;
        private String storeName;
        private List<MenuDTO> menus;

        public StoreMenuDTO(Store store, List<Menu> menus) {
            this.storeId = store.getId();
            this.storeName = store.getName();
            this.menus = menuDTOList(menus);
        }

        private List<MenuDTO> menuDTOList(List<Menu> menus) {
            List<MenuDTO> menuDTOList = new ArrayList<>();
            for (Menu menu : menus) {
                MenuDTO menuDTO = new MenuDTO();
                menuDTO.setId(menu.getId());
                menuDTO.setCategory(menu.getCategory());
                menuDTO.setName(menu.getName());
                menuDTO.setDescription(menu.getDescription());
                menuDTO.setPrice(menu.getPrice());
                menuDTO.setImgSrc(menu.getImgSrc());

                menuDTOList.add(menuDTO);
            }
            return menuDTOList;
        }

        @Data
        public class MenuDTO {
            private int id;
            private String category;
            private String name;
            private String description;
            private int price;
            private String imgSrc;
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
