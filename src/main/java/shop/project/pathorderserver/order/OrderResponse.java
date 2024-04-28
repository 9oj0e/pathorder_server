package shop.project.pathorderserver.order;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class OrderResponse {
    @Data // 주문내역 목록보기
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
    public static class DetailDTO {
        private String storeName;
        private String storeTel;
        private Timestamp orderTime;
        private String request;
        private int totalAmount;

        public DetailDTO(Order order) {
            this.storeName = order.getStoreName();
            this.storeTel = order.getStore().getTel();
            this.orderTime = order.getCreatedAt();
            this.request = order.getRequest();
            this.totalAmount = order.getTotalAmount();
        }
    }
}
