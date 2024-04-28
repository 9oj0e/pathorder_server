package shop.project.pathorderserver.store;

import lombok.Data;
import shop.project.pathorderserver._core.utils.FormatUtil;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.Option;

import java.util.List;

public class StoreResponse {

    @Data // 매장 목록보기
    public static class ListingsDTO {
        private int id;
        private String imgFilename;
        private String name;
        private int distance; // 거리 계산 TODO: 지도 api
        private int likeCount;

        public ListingsDTO(Store store) {
            this.id = store.getId();
            this.imgFilename = store.getImgFilename();
            this.name = store.getName();
            this.distance = 163;
            this.likeCount = 181;
        }
    }

    @Data // 매장 상세보기
    public static class DetailDTO {
        private int id;
        private String imgFilename;
        private String name;
        private String intro;
        private String openingTime;
        private String closingTime;
        private String closedDay;
        private String address;

        public DetailDTO(Store store) {
            this.id = store.getId();
            this.imgFilename = store.getImgFilename();
            this.name = store.getName();
            this.intro = store.getIntro();
            this.openingTime = store.getOpeningTime();
            this.closingTime = store.getClosingTime();
            this.closedDay = store.getClosedDay();
            this.address = store.getAddress();
        }
    }

    @Data // 매장 상세보기 - 사업자 정보
    public static class BizInfoDTO {
        private String ownerName;
        private String ownerTel;
        private String ownerEmail;
        private String bizNum;

        public BizInfoDTO(Store store) {
            this.ownerName = store.getOwnerName();
            this.ownerTel = store.getTel();
            this.ownerEmail = store.getOwnerEmail();
            this.bizNum = store.getBizNum();
        }

        public String getOwnerTel() {
            return FormatUtil.pNumFormatter(ownerTel);
        }
    }

    @Data // 매장 메뉴 목록보기
    public static class MenuListDTO {
        // 매장 정보
        private int storeId;
        private String storeName;
        // 메뉴 정보
        private List<MenuDTO> menuList;

        public MenuListDTO(Store store, List<Menu> menus) {
            this.storeId = store.getId();
            this.storeName = store.getName();
            this.menuList = menus.stream().map(MenuDTO::new).toList();
        }

        @Data
        private class MenuDTO {
            private int id;
            private String category;
            private String name;
            private String imgFilename;
            private String description;
            private int price;

            public MenuDTO(Menu menu) {
                this.id = menu.getId();
                this.category = menu.getCategory();
                this.name = menu.getName();
                this.imgFilename = menu.getImgFilename();
                this.description = menu.getDescription();
                this.price = menu.getPrice();
            }
        }
    }

    @Data // 메뉴 옵션보기
    public static class MenuOptionDTO {
        // 매장 정보
        private int storeId;
        private String storeName;
        // 메뉴 정보
        private int menuId;
        private String menuName;
        private String menuImgFilename;
        private int menuPrice;
        // 옵션 정보
        private List<OptionDTO> optionList;

        public MenuOptionDTO(Store store, Menu menu, List<Option> options) {
            this.storeId = store.getId();
            this.storeName = store.getName();
            this.menuId = menu.getId();
            this.menuImgFilename = menu.getImgFilename();
            this.menuName = menu.getName();
            this.menuPrice = menu.getPrice();
            this.optionList = options.stream().map(OptionDTO::new).toList();
        }

        @Data
        private class OptionDTO {
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
