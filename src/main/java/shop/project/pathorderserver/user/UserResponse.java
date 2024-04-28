package shop.project.pathorderserver.user;

import lombok.Builder;
import lombok.Data;
import shop.project.pathorderserver.order.Order;
import shop.project.pathorderserver.order.OrderMenu;
import shop.project.pathorderserver.order.OrderStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

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

    @Data // 주문내역 목록보기 TODO: refactor
    public static class OrderListDTO {
        private List<OrderDTO> orders;

        @Data
        public static class OrderDTO {
            private int orderId;
            private Timestamp orderTime;
            private String storeName;
            private int totalAmount;
            private OrderStatus status;
            private List<OrderListDTO.OrderMenuDTO> orderMenus;

            public OrderDTO(Order order) {
                this.orderId = order.getId();
                this.orderTime = order.getCreatedAt();
                this.storeName = order.getStoreName();
                this.totalAmount = order.getTotalAmount();
                this.status = order.getStatus();
                // TODO: stream.collect.reversed() -> Query "ORDER BY ASC" ?
                this.orderMenus = order.getOrderMenus().stream().map(OrderListDTO.OrderMenuDTO::new).collect(Collectors.toList()).reversed();
            }

            @Data
            public static class OrderMenuDTO {
                private int menuId;
                private String menuName;
            }
        }

        public OrderListDTO(List<Order> orderList) {
            this.orders = orderList.stream().map(OrderDTO::new).collect(Collectors.toList());
        }

        @Data
        public static class OrderMenuDTO {
            private int menuId;
            private String menuName;

            public OrderMenuDTO(OrderMenu orderMenu) {
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
        private int totalAmount;

        public OrderDetailDTO(Order order) {
            this.storeName = order.getStoreName();
            this.storeTel = order.getStore().getTel();
            this.orderTime = order.getCreatedAt();
            this.request = order.getRequest();
            this.totalAmount = order.getTotalAmount();
        }
    }
}
