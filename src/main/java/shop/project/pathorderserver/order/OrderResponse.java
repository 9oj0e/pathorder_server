package shop.project.pathorderserver.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class OrderResponse {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SaveDTO {
        // Store
        private int storeId;
        private String storeName;
        // customer
        private int customerId;
        private String customerName;
        // etc
        private String request;
        private int totalAmount;
        private OrderStatus status;
        // Menu
        private List<OrderMenuDTO> orderMenuList;

        @Builder
        public SaveDTO(Order order, List<OrderMenu> orderMenuList, List<OrderOption> orderOptionList) {
            this.storeId = order.getStore().getId();
            this.storeName = order.getStoreName();
            this.customerId = order.getCustomer().getId();
            this.customerName = order.getCustomerName();
            this.request = order.getRequest();
            this.totalAmount = order.getTotalAmount();
            this.status = order.getStatus();
            this.orderMenuList = orderMenuList.stream()
                    .map(orderMenu -> new OrderMenuDTO(orderMenu, orderOptionList))
                    .toList();
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class OrderMenuDTO {
            private String name;
            private int qty;
            private int price;
            private List<OrderOptionDTO> orderOptionList;

            @Builder
            public OrderMenuDTO(OrderMenu orderMenu, List<OrderOption> orderOptionList) {
                this.name = orderMenu.getName();
                this.qty = orderMenu.getQty();
                this.price = orderMenu.getPrice();
                this.orderOptionList = orderOptionList.stream().map(OrderOptionDTO::new).toList();

            }

            @NoArgsConstructor
            @AllArgsConstructor
            @Data
            public static class OrderOptionDTO {
                private String name;
                private int price;

                @Builder
                public OrderOptionDTO(OrderOption orderOption) {
                    this.name = orderOption.getName();
                    this.price = orderOption.getPrice();
                }
            }
        }
    }
}
