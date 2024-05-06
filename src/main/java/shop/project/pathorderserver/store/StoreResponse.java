package shop.project.pathorderserver.store;

import lombok.Builder;
import lombok.Data;
import shop.project.pathorderserver._core.utils.FileUtil;
import shop.project.pathorderserver._core.utils.FormatUtil;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuOption;
import shop.project.pathorderserver.order.Order;
import shop.project.pathorderserver.order.OrderMenu;
import shop.project.pathorderserver.order.OrderMenuOption;
import shop.project.pathorderserver.order.OrderStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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

    @Data // 매장 관리자 - 매장 메뉴 등록
    public static class CreateMenuDTO {
        private int id;
        private int price; // 메뉴 하나의 가격
        private String category; // 각 메뉴가 포함되는 카테고리, 점주가 직접 작성
        private String name; // 메뉴 이름
        private String imgFilename;
        private String description; // 메뉴 설명
        private String registeredAt; // 메뉴 등록일

        public CreateMenuDTO(Menu menu) {
            this.id = menu.getId();
            this.price = menu.getPrice();
            this.category = menu.getCategory();
            this.name = menu.getName();
            this.imgFilename = menu.getImgFilename();
            this.description = menu.getDescription();
            this.registeredAt = FormatUtil.dateFormatter(menu.getRegisteredAt());
        }
    }

    @Data // 매장 관리자 - 메뉴 목록보기
    public static class MenuListDTO {
        private List<MenuDTO> menuList;

        public MenuListDTO(List<Menu> menus) {
            this.menuList = menus.stream().map(MenuDTO::new).toList();
        }

        @Data
        private static class MenuDTO {
            private int id;
            private String imgFilePath;
            private String name;
            private int price;

            public MenuDTO(Menu menu) {
                this.id = menu.getId();
                this.imgFilePath = FileUtil.getFilePath(menu.getImgFilename());
                this.name = menu.getName();
                this.price = menu.getPrice();
            }

            public String getPrice() {
                return FormatUtil.decimalFormatter(price) + "원";
            }
        }
    }

    @Data // 매장 메뉴 및 옵션 정보 보기
    public static class MenuDetailDTO {
        private int id;
        private int price; // 메뉴 하나의 가격
        private String category; // 각 메뉴가 포함되는 카테고리, 점주가 직접 작성
        private String name; // 메뉴 이름
        // private String imgFilename;
        private String description; // 메뉴 설명
        private String registeredAt; // 메뉴 등록일
        private List<MenuOptionDTO> menuOptionList = new ArrayList<>();

        public MenuDetailDTO(Menu menu, List<MenuOption> menuOptions) {
            this.id = menu.getId();
            this.price = menu.getPrice();
            this.category = menu.getCategory();
            this.name = menu.getName();
            this.description = menu.getDescription();
            this.registeredAt = FormatUtil.dateFormatter(menu.getRegisteredAt());
            // this.menuOptionList = menuOptions.stream().map(MenuOptionDTO::new).toList();
            for (int i = 0; i < menuOptions.size(); i++) {
                this.menuOptionList.add(new MenuOptionDTO(menuOptions.get(i)));
            }
        }

        @Data
        public static class MenuOptionDTO {
            private int id;
            private int price;
            private String name;
            private boolean isRequired;
            // private String createdAt;

            public MenuOptionDTO(MenuOption menuOption) {
                this.id = menuOption.getId();
                this.price = menuOption.getPrice();
                this.name = menuOption.getName();
                this.isRequired = menuOption.isRequired();
                // this.createdAt = FormatUtil.dateFormatter(menuOption.getCreatedAt());
            }
        }
    }

    @Data // 매장 메뉴 수정
    public static class UpdateMenuDTO {
        private int price; // 메뉴 하나의 가격
        private String category; // 각 메뉴가 포함되는 카테고리, 점주가 직접 작성
        private String name; // 메뉴 이름
        private String imgFilename;
        private String description; // 메뉴 설명

        public UpdateMenuDTO(Menu menu) {
            this.price = menu.getPrice();
            this.category = menu.getCategory();
            this.name = menu.getName();
            this.imgFilename = menu.getImgFilename();
            this.description = menu.getDescription();
        }
    }

    @Data // 매장 관리자 - 매장 메뉴 옵션 등록
    public static class CreateMenuOptionDTO {
        // 메뉴 정보
        private int menuId;
        // 옵션 정보
        private int id;
        private int price;
        private String name;
        private boolean isRequired;

        public CreateMenuOptionDTO(int menuId, MenuOption menuOption) {
            this.menuId = menuId;
            this.id = menuOption.getId();
            this.price = menuOption.getPrice();
            this.name = menuOption.getName();
            this.isRequired = menuOption.isRequired();
        }
    }

    @Data // 매장 관리자 - 매장 메뉴 옵션 수정
    public static class UpdateMenuOptionDTO {
        // 메뉴 정보
        private int menuId;
        // 옵션 정보
        private int id;
        private int price;
        private String name;
        private boolean isRequired;

        public UpdateMenuOptionDTO(MenuOption menuOption) {
            setMenuId(menuOption.getMenu().getId());
            setId(menuOption.getId());
            setPrice(menuOption.getPrice());
            setName(menuOption.getName());
            setRequired(menuOption.isRequired());
        }
    }

    @Data // 매장 관리자 - 주문내역 목록보기
    public static class OrderListDTO {
        private List<OrderDTO> orderList;

        public OrderListDTO(List<Order> orders) {
            this.orderList = orders.stream().map((Order order) -> new OrderDTO(order, order.getOrderMenus())).toList();
        }

        @Data
        public static class OrderDTO {
            // 손님 정보
            private String customerNickname;
            // 주문 정보
            private int orderId;
            private OrderStatus status;
            private Timestamp createdAt;
            private List<OrderMenuDTO> orderMenus;
            private int totalPrice;

            public OrderDTO(Order order, List<OrderMenu> orderMenus) {
                this.orderId = order.getId();
                this.status = order.getStatus();
                this.createdAt = order.getCreatedAt();
                this.orderMenus = orderMenus.stream().map(OrderMenuDTO::new).toList();
                this.totalPrice = order.getTotalPrice();
                this.customerNickname = order.getCustomerNickname();
            }

            @Data
            public class OrderMenuDTO {
                private String name;

                public OrderMenuDTO(OrderMenu orderMenu) {
                    this.name = orderMenu.getName();
                }
            }

            public String getCreatedAt() {
                return FormatUtil.timeFormatter(createdAt);
            }

            public String getOrderMenus() {
                StringJoiner orderMenusWithComma = new StringJoiner(", ");
                for (OrderMenuDTO orderMenu : orderMenus) {
                    orderMenusWithComma.add(orderMenu.getName());
                }
                return FormatUtil.stringFormatter(orderMenusWithComma.toString());
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

        public String getCreatedAt() {
            LocalDateTime localDateTime = createdAt.toLocalDateTime();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm");
            return localDateTime.format(formatter);
        }

        public String getTotalPrice() {
            return FormatUtil.decimalFormatter(totalPrice);
        }

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

            public String getTotalPrice() {
                return FormatUtil.decimalFormatter(totalPrice);
            }

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

    @Data
    public static class OrdersDTO {
        private int orderId;
        private OrderStatus status;
        private String customerNickname;
        private List<OrderMenuDTO> menuList;
        private Timestamp createdAt;

        public String getCreatedAt() {
            LocalDateTime localDateTime = createdAt.toLocalDateTime();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a h:mm");
            return localDateTime.format(formatter);
        }

        @Builder
        public OrdersDTO(Order order, List<OrderMenu> menuList) {
            this.orderId = order.getId();
            this.status = order.getStatus();
            this.customerNickname = order.getCustomerNickname();
            this.menuList = menuList.stream().map(orderMenu1 -> {
                return new OrderMenuDTO(orderMenu1);
            }).toList();
            this.createdAt = order.getCreatedAt();
        }

        @Data
        public static class OrderMenuDTO {
            private String name;
            private int qty;

            public OrderMenuDTO(OrderMenu orderMenu) {
                this.name = orderMenu.getName();
                this.qty = orderMenu.getQty();
            }
        }
    }
}
