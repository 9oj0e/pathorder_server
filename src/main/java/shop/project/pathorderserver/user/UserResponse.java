package shop.project.pathorderserver.user;

import lombok.Builder;
import lombok.Data;
import shop.project.pathorderserver._core.utils.FormatUtil;
import shop.project.pathorderserver.order.Order;
import shop.project.pathorderserver.order.OrderMenu;
import shop.project.pathorderserver.order.OrderMenuOption;
import shop.project.pathorderserver.order.OrderStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    @Data // 회원가입 완료시
    public static class JoinDTO {
        // private int id;
        private String username;
        private String nickname;

        @Builder
        public JoinDTO(String username, String nickname) {
            this.username = username;
            this.nickname = nickname;
        }
    }

    @Data // 로그인시
    public static class LoginDTO {
        private int id;
        private String nickname; // 닉네임 응답

        @Builder
        public LoginDTO(int id, String nickname) {
            this.id = id;
            this.nickname = nickname;
        }
    }

    @Data // 회원 정보 조회시
    public static class UserDTO {
        // 회원 정보
        private int id;
        private String nickname;
        // 개인 정보
        private String email;
        private String tel;
        // 사진
        private String imgFilename;

        @Builder
        public UserDTO(int id, String nickname, String email, String tel, String imgFilename) {
            this.id = id;
            this.nickname = nickname;
            this.email = email;
            this.tel = tel;
            this.imgFilename = imgFilename;
        }

        public String getTel() {
            return FormatUtil.pNumFormatter(tel);
        }
    }

    @Data // 회원 정보 수정시
    public static class UpdateDTO {
        // 회원 정보
        private int id;
        private String nickname;
        // 개인 정보
        private String email;
        private String tel;

        @Builder
        public UpdateDTO(int id, String nickname, String email, String tel) {
            this.id = id;
            this.nickname = nickname;
            this.email = email;
            this.tel = tel;
        }
    }

    @Data // 사진 수정시
    public static class ImgDTO {
        // 사진
        private String newImgFilePath;

        public ImgDTO(String newImgFilePath) {
            this.newImgFilePath = newImgFilePath;
        }
    }

    @Data // 주문내역 목록보기
    public static class OrderListDTO {
        private List<OrderDTO> orderList;

        public OrderListDTO(List<Order> orders) {
            this.orderList = orders.stream().map(OrderDTO::new).toList();
        }

        @Data
        private class OrderDTO {
            // 매장 정보
            private int storeId;
            private String storeName;
            // 주문 정보
            private int id;
            private OrderStatus status;
            private Timestamp createdAt;
            private List<OrderListDTO.OrderMenuDTO> orderMenuList;
            private int totalPrice;

            private OrderDTO(Order order) {
                this.id = order.getId();
                this.createdAt = order.getCreatedAt();
                this.storeId = order.getStore().getId();
                this.storeName = order.getStoreName();
                this.totalPrice = order.getTotalPrice();
                this.status = order.getStatus();
                this.orderMenuList = order.getOrderMenus().stream().map(OrderListDTO.OrderMenuDTO::new).toList();
            }

            public String getTotalPrice() { // 19500 -> 19,500 변환
                return FormatUtil.decimalFormatter(totalPrice);
            }

            public String getCreatedAt() { // 월/일 시간:분:초
                return FormatUtil.timeFormatter(createdAt);
            }
        }

        @Data
        private class OrderMenuDTO {
            private int id;
            private String name;
            private int totalPrice;

            private OrderMenuDTO(OrderMenu orderMenu) {
                this.id = orderMenu.getId();
                this.name = orderMenu.getName();
                this.totalPrice = orderMenu.getTotalPrice();
            }

            public String getTotalPrice() { // 19500 -> 19,500 변환
                return FormatUtil.decimalFormatter(totalPrice);
            }
        }
    }

    @Data // 주문내역 상세보기
    public static class OrderDetailDTO {
        // 매장 정보
        private int storeId;
        private String storeName;
        private String storeTel;
        // 주문 정보
        private int id;
        private String request;
        private Timestamp createdAt;
        private OrderStatus status;
        private List<OrderMenuDTO> orderMenuList;
        private int totalPrice;

        public OrderDetailDTO(Order order, List<OrderMenu> orderMenus) {
            this.id = order.getId();
            this.storeName = order.getStoreName();
            this.storeId = order.getStore().getId();
            this.storeTel = order.getStore().getTel();
            this.createdAt = order.getCreatedAt();
            this.status = order.getStatus();
            this.request = order.getRequest();
            this.totalPrice = order.getTotalPrice();
            this.orderMenuList = orderMenus.stream().map(OrderMenuDTO::new).toList();
        }

        @Data
        private class OrderMenuDTO {
            private int id;
            private String name;
            private int price;
            private List<OrderMenuOptionDTO> orderMenuOptionList;
            private int qty;
            private int totalPrice;

            public OrderMenuDTO(OrderMenu orderMenu) {
                this.id = orderMenu.getId();
                this.name = orderMenu.getName();
                this.price = orderMenu.getPrice();
                this.qty = orderMenu.getQty();
                this.totalPrice = orderMenu.getTotalPrice();
                this.orderMenuOptionList = orderMenu.getOrderMenuOptions().stream().map(OrderMenuOptionDTO::new).toList();
                /*
                List<Integer> optionPriceList = orderMenuOptionList.stream().map(OrderMenuOptionDTO::getPrice).toList();
                for (int optionPrice : optionPriceList) {
                    totalPrice += optionPrice;
                }
                */
            }

            @Data
            private class OrderMenuOptionDTO {
                private int id;
                private String name;
                private int price;

                public OrderMenuOptionDTO(OrderMenuOption orderMenuOption) {
                    this.id = orderMenuOption.getOrderMenu().getId();
                    this.name = orderMenuOption.getName();
                    this.price = orderMenuOption.getPrice();
                }

                public String getPrice() { // 19500 -> 19,500 변환
                    return FormatUtil.decimalFormatter(price);
                }
            }

            public String getTotalPrice() { // 19500 -> 19,500 변환
                return FormatUtil.decimalFormatter(totalPrice);
            }

            public String getPrice() { // 19500 -> 19,500 변환
                return FormatUtil.decimalFormatter(price);
            }
        }

        public String getStoreTel() {
            return FormatUtil.pNumFormatter(storeTel);
        }

        public String getCreatedAt() {
            return FormatUtil.timeFormatter(createdAt);
        }

        public String getTotalPrice() { // 19500 -> 19,500 변환
            return FormatUtil.decimalFormatter(totalPrice);
        }
    }

    @Data // 주문하기
    public static class OrderDTO {
        // 손님 정보
        private int customerId;
        private String customerNickname;
        // 매장 정보
        private int storeId;
        private String storeName;
        // 주문 정보
        private int id;
        private String request;
        private OrderStatus status;
        private List<OrderMenuDTO> orderMenuList;
        private int totalPrice;

        public OrderDTO(Order order, List<OrderMenu> orderMenus, List<OrderMenuOption> orderMenuOptions) {
            this.id = order.getId();
            this.storeId = order.getStore().getId();
            this.storeName = order.getStoreName();
            this.customerId = order.getCustomer().getId();
            this.customerNickname = order.getCustomerNickname();
            this.request = order.getRequest();
            this.totalPrice = order.getTotalPrice();
            this.status = order.getStatus();
            this.orderMenuList = orderMenus.stream()
                    .map(orderMenu -> new OrderMenuDTO(orderMenu, orderMenuOptions))
                    .toList();
        }

        @Data
        public static class OrderMenuDTO {
            private int id;
            // private int menuId;
            private String name;
            private int price;
            private List<OrderMenuOptionDTO> orderMenuOptionList = new ArrayList<>();
            private int qty;
            private int totalPrice;

            public OrderMenuDTO(OrderMenu orderMenu, List<OrderMenuOption> orderMenuOptions) {
                this.id = orderMenu.getId();
                this.name = orderMenu.getName();
                this.price = orderMenu.getPrice();
                this.qty = orderMenu.getQty();
                this.totalPrice = orderMenu.getTotalPrice();
                for (int i = 0; i < orderMenuOptions.size(); i++) {
                    OrderMenuOptionDTO orderMenuOption = new OrderMenuOptionDTO(orderMenuOptions.get(i));
                    if (id == orderMenuOptions.get(i).getOrderMenu().getId()) {
                        // this.orderMenuOptionList = orderMenuOptions.stream().map(OrderMenuOptionDTO::new).toList();
                        this.orderMenuOptionList.add(orderMenuOption);
                    }
                }
            }

            @Data
            public static class OrderMenuOptionDTO {
                private String name;
                private int price;

                public OrderMenuOptionDTO(OrderMenuOption orderMenuOption) {
                    this.name = orderMenuOption.getName();
                    this.price = orderMenuOption.getPrice();
                }

                public String getPrice() {
                    return FormatUtil.decimalFormatter(price);
                }
            }

            public String getPrice() {
                return FormatUtil.decimalFormatter(price);
            }
        }

        public String getTotalPrice() {
            return FormatUtil.decimalFormatter(totalPrice);
        }
    }
}
