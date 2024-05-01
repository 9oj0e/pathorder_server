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
            private int orderId;
            private Timestamp orderTime;
            private String storeName;
            private int totalPrice;
            private OrderStatus status;
            private List<OrderListDTO.OrderMenuDTO> orderMenuList;

            private OrderDTO(Order order) {
                this.orderId = order.getId();
                this.orderTime = order.getCreatedAt();
                this.storeName = order.getStoreName();
                this.totalPrice = order.getTotalPrice();
                this.status = order.getStatus();
                this.orderMenuList = order.getOrderMenus().stream().map(OrderListDTO.OrderMenuDTO::new).toList();
            }

            public String getTotalPrice() { // 19500 -> 19,500 변환
                return FormatUtil.decimalFormatter(totalPrice);
            }

            public String getOrderTime() { // 월/일 시간:분:초
                return FormatUtil.timeFormatter(orderTime);
            }
        }

        @Data
        private class OrderMenuDTO {
            private int menuId;
            private String menuName;

            private OrderMenuDTO(OrderMenu orderMenu) {
                this.menuId = orderMenu.getId();
                this.menuName = orderMenu.getName();
            }
        }
    }

    @Data // 주문내역 상세보기
    public static class OrderDetailDTO {
        private String storeName;
        private String storeTel;
        private Timestamp orderTime;
        private String request;
        private List<OrderMenuDTO> orderMenuList;
        private int totalPrice;

        public OrderDetailDTO(Order order, List<OrderMenu> orderMenus) {
            this.storeName = order.getStoreName();
            this.storeTel = order.getStore().getTel();
            this.orderTime = order.getCreatedAt();
            this.request = order.getRequest();
            this.totalPrice = order.getTotalPrice();
            this.orderMenuList = orderMenus.stream().map(OrderMenuDTO::new).toList();
        }

        @Data
        private class OrderMenuDTO {
            private int menuId;
            private String menuName;
            private int menuPrice;
            private int totalPrice;
            private List<OrderMenuOptionDTO> orderMenuOptionList;

            public OrderMenuDTO(OrderMenu orderMenu) {
                this.menuId = orderMenu.getId();
                this.menuName = orderMenu.getName();
                this.menuPrice = orderMenu.getPrice();
                this.totalPrice = orderMenu.getPrice();
                this.orderMenuOptionList = orderMenu.getOrderMenuOptions().stream().map(OrderMenuOptionDTO::new).toList();
                List<Integer> optionPriceList = orderMenuOptionList.stream().map(OrderMenuOptionDTO::getPrice).toList();
                for (int optionPrice : optionPriceList) {
                    totalPrice += optionPrice;
                }
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
            }
            /*
            public void setTotalPrice() { // 총액 계산
                this.totalPrice += menuPrice;
                List<Integer> optionPriceList = menuOptionList.stream().map(orderOptionDTO -> orderOptionDTO.getPrice()).toList();
                for (int optionPrice : optionPriceList) {
                    totalPrice += optionPrice;
                }
            }
            */
        }

        public String getStoreTel() {
            return FormatUtil.pNumFormatter(storeTel);
        }

        public String getOrderTime() {
            return FormatUtil.timeFormatter(orderTime);
        }

        public String getTotalPrice() { // 19500 -> 19,500 변환
            return FormatUtil.decimalFormatter(totalPrice);
        }
    }

    @Data // 주문하기
    public static class OrderDTO {
        private int id;
        private int storeId;
        private String storeName;
        private int customerId;
        private String customerNickname;
        private String request;
        private int totalPrice;
        private OrderStatus status;
        private List<OrderMenuDTO> orderMenuList;

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

            public OrderMenuDTO(OrderMenu orderMenu, List<OrderMenuOption> orderMenuOptions) {
                this.id = orderMenu.getId();
                this.name = orderMenu.getName();
                this.price = orderMenu.getPrice();
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
