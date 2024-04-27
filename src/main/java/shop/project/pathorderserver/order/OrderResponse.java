package shop.project.pathorderserver.order;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuResponse;
import shop.project.pathorderserver.menu.Option;
import shop.project.pathorderserver.store.Store;

import java.util.List;

public class OrderResponse {
    // 메뉴 옵션 DTO
    @Data
    public static class MenuOptionDTO {
        private int storeId;
        private String storeName;
        private int menuId;
        private String menuImgFilename;
        private String menuName;
        private int menuPrice;
        private List<OptionDTO> options;

        public MenuOptionDTO(Store store, Menu menu, List<Option> options) {
            this.storeId = store.getId();
            this.storeName = store.getName();
            this.menuId = menu.getId();
            this.menuImgFilename = menu.getImgFilename();
            this.menuName = menu.getName();
            this.menuPrice = menu.getPrice();
            this.options = optionDTOList(options);
        }

        private List<OptionDTO> optionDTOList(List<Option> options) {
            List<OptionDTO> optionDTOList = options.stream().map(OptionDTO::new).toList();
            return optionDTOList;
        }

        @Data
        public class OptionDTO {
            private int id;
            private String name;
            private int price;
            private boolean isRequired;

            public OptionDTO(Option option) {
                this.id = option.getId();
                this.name = option.getName();
                this.price = option.getPrice();
                this.isRequired = option.isRequired();
            }
        }
    }
}
