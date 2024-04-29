package shop.project.pathorderserver.order;

import lombok.Data;

import java.util.List;

public class OrderRequest {
    @Data
    public static class OrderDTO {
        private int storeId;
        private String storeName;
        private int customerId;
        private String customerName;
        private String request;
        private int totalPrice;
        private List<OrderMenuDTO> orderMenuList;

        @Data
        public static class OrderMenuDTO {
            private String name;
            private int price;
            private List<OrderMenuOptionDTO> orderMenuOptionList;
        }

        @Data
        public static class OrderMenuOptionDTO {
            private String name;
            private int price;
        }
    }
    // TODO: 주문 수정 (매장 측: 주문 상태 변경)
}
