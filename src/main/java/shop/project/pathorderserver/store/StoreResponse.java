package shop.project.pathorderserver.store;

import lombok.Data;
import shop.project.pathorderserver._core.utils.FormatUtil;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuOption;
import shop.project.pathorderserver.order.Order;
import shop.project.pathorderserver.order.OrderMenu;
import shop.project.pathorderserver.order.OrderMenuOption;
import shop.project.pathorderserver.order.OrderStatus;

import java.sql.Timestamp;
import java.util.List;

public class StoreResponse {
    @Data // 매장 등록
    public static class JoinDTO {
        // 회원 정보
        private String username;
        // 사업자 정보
        private String ownerName;
        private String bizNum;
        // 매장 정보
        private String name;

        public JoinDTO(Store store) {
            this.username = store.getUsername();
            this.ownerName = store.getOwnerName();
            this.bizNum = store.getBizNum();
            this.name = store.getName();
        }
    }

    @Data // 매장 목록보기
    public static class StoreListDTO {
        private int id;
        private String imgFilename;
        private String name;
        private int distance; // 거리 계산 TODO: 지도 api
        private int likeCount;

        public StoreListDTO(Store store) {
            this.id = store.getId();
            this.imgFilename = store.getImgFilename();
            this.name = store.getName();
            this.distance = 163;
            this.likeCount = 181;
        }
    }

    @Data // 매장 상세보기
    public static class StoreInfoDTO {
        private int id;
        private String imgFilename;
        private String name;
        private String intro;
        private String openingTime;
        private String closingTime;
        private String closedDay;
        private String address;

        public StoreInfoDTO(Store store) {
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
    public static class StoreBizInfoDTO {
        private String ownerName;
        private String ownerTel;
        private String ownerEmail;
        private String bizNum;

        public StoreBizInfoDTO(Store store) {
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
    public static class StoreMenuListDTO {
        // 매장 정보
        private int storeId;
        private String storeName;
        // 메뉴 정보
        private List<MenuDTO> menuList;

        public StoreMenuListDTO(Store store, List<Menu> menus) {
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
    public static class StoreMenuOptionDTO {
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

        public StoreMenuOptionDTO(Store store, Menu menu, List<MenuOption> options) {
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

            public OptionDTO(MenuOption option) {
                this.id = option.getId();
                this.name = option.getName();
                this.price = option.getPrice();
                this.isRequired = option.isRequired();
            }
        }
    }

    /*------------------------------------------------------------------------------------- 매장 관리자 -----------------*/
    @Data // 매장 관리자 - 매장 정보 보기
    public static class StoreDTO {
        private int id;
        // 매장 정보
        private String imgFilename;
        private String name;
        private String tel;
        private String intro;
        private String openingTime;
        private String closingTime;
        private String closedDay;
        private String address;
        // 사업자 정보
        private String ownerName;
        private String ownerTel;
        private String ownerEmail;
        private String bizNum;
        // 로그인 정보
        private String username;
        private String password;

        public StoreDTO(Store store) {
            this.id = store.getId();
            this.imgFilename = store.getImgFilename();
            this.name = store.getName();
            this.tel = store.getTel();
            this.intro = store.getIntro();
            this.openingTime = store.getOpeningTime();
            this.closingTime = store.getClosingTime();
            this.closedDay = store.getClosedDay();
            this.address = store.getAddress();
            this.ownerName = store.getOwnerName();
            this.ownerTel = store.getOwnerTel();
            this.ownerEmail = store.getOwnerEmail();
            this.bizNum = store.getBizNum();
            this.username = store.getUsername();
            this.password = store.getPassword();
        }
    }

    @Data // TODO: 매장 메뉴 등록
    public static class CreateMenuDTO {
    }

    @Data // 매장 관리자 - 메뉴 목록보기
    public static class OwnerMenuListDTO {
        private List<MenuDTO> menuList;

        public OwnerMenuListDTO(List<Menu> menus) {
            this.menuList = menus.stream().map(MenuDTO::new).toList();
        }

        @Data
        private static class MenuDTO {
            private int id;
            private String imgFilename;
            private String name;
            private int price;

            public MenuDTO(Menu menu) {
                this.id = menu.getId();
                this.imgFilename = menu.getImgFilename();
                this.name = menu.getName();
                this.price = menu.getPrice();
            }
        }
    }

    @Data // TODO: 매장 메뉴 수정
    public static class MenuDTO {
    }

    @Data // TODO: 매장 메뉴 수정
    public static class UpdateMenuDTO {
    }

    @Data // TODO: 매장 메뉴 옵션 등록
    public static class CreateMenuOptionDTO {
    }

    @Data // TODO: 매장 메뉴 옵션 수정
    public static class UpdateMenuOptionDTO {
    }

    @Data // 매장 관리자 - 주문내역 목록보기
    public static class OrderListDTO {
        private List<OrderDTO> orderList;

        public OrderListDTO(List<Order> orders) {
            this.orderList = orders.stream().map(OrderDTO::new).toList();
        }

        @Data
        public class OrderDTO {
            // 손님 정보
            private String customerNickname;
            // 주문 정보
            private OrderStatus status;
            private Timestamp createdAt;
            private List<OrderMenu> orderMenus;
            private int totalPrice;

            public OrderDTO(Order order) {
                this.status = order.getStatus();
                this.createdAt = order.getCreatedAt();
                this.orderMenus = order.getOrderMenus();
                this.totalPrice = order.getTotalPrice();
                this.customerNickname = order.getCustomerNickname();
            }
        }
    }

    @Data // 매장 관리자 - 주문내역 상세보기
    public static class OrderDetailDTO {
        // 손님 정보
        private int customerId;
        private String customerNickname;
        private String customerTel;
        // 주문 정보
        private Timestamp createdAt;
        private OrderStatus status;
        private List<OrderMenuDTO> orderMenuList;
        private int totalPrice;

        public OrderDetailDTO(Order order, List<OrderMenu> orderMenus) {
            this.createdAt = order.getCreatedAt();
            this.customerNickname = order.getCustomerNickname();
            this.customerTel = order.getCustomer().getTel();
            this.status = order.getStatus();
            this.orderMenuList = orderMenus.stream().map(orderMenu -> new OrderMenuDTO(orderMenu, orderMenu.getOrderMenuOptions())).toList();
            this.totalPrice = order.getTotalPrice();
        }

        @Data
        private static class OrderMenuDTO {
            private String name;
            private int price;
            private List<OrderMenuOptionDTO> orderMenuOptionList;
            private int qty;
            private int totalPrice;

            public OrderMenuDTO(OrderMenu orderMenu, List<OrderMenuOption> orderMenuOptions) {
                this.name = orderMenu.getName();
                this.price = orderMenu.getPrice();
                this.qty = orderMenu.getQty();
                this.orderMenuOptionList = orderMenuOptions.stream().map(OrderMenuOptionDTO::new).toList();
                this.totalPrice = orderMenu.getTotalPrice();
            }

            @Data
            public static class OrderMenuOptionDTO {
                private String name;
                private int price;

                public OrderMenuOptionDTO(OrderMenuOption orderMenuOption) {
                    this.name = orderMenuOption.getName();
                    this.price = orderMenuOption.getPrice();
                }
            }
        }
    }

    @Data // 매장 주문 업데이트
    public static class UpdateOrderDTO {
        private OrderStatus status;

        public UpdateOrderDTO(Order order) {
            this.status = order.getStatus();
        }
    }
}
