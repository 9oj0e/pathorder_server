package shop.project.pathorderserver.order;

import lombok.Data;

import java.util.List;

public class OrderRequest {
    @Data
    public static class SaveDTO {
        private int storeId;
        private String storeName;
        private int customerId;
        private String customerName;
        private String request;
        private OrderStatus status;
        private int totalAmount;
        private List<OrderMenuDTO> orderMenuList;

        @Data
        public static class OrderMenuDTO {
            private String name;
            private int quantity;
            private int price;
            private List<OrderOptionDTO> orderOptionList;
        }

        @Data
        public static class OrderOptionDTO {
            private int orderMenuId;
            private String name;
            private int price;
        }
    }
}
