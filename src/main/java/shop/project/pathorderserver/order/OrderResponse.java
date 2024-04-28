package shop.project.pathorderserver.order;

import lombok.Builder;
import lombok.Data;

import java.util.List;

public class OrderResponse {

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
            this.orderMenuList = orderMenuList.stream().map(OrderMenuDTO::new).toList();
        }

        @Data
        public static class OrderMenuDTO {
            private String name;
            private int qty;
            private int price;
            private List<OrderOptionDTO> orderOptionList;

            @Builder
            public OrderMenuDTO(OrderMenu orderMenu) {
                this.name = orderMenu.getName();
                this.qty = orderMenu.getQty();
                this.price = orderMenu.getPrice();
            }

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
