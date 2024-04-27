package shop.project.pathorderserver.order;

import lombok.Data;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.Option;
import shop.project.pathorderserver.store.Store;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class OrderResponse {
    // 메뉴 옵션 DTO
    @Data
    public static class MenuOptionDTO {
        private int storeId;
        private String storeName;
        private int menuId;
        private String menuImgFilename;
        private String menuName;
        private int menuPrice;
        private List<OptionDTO> options;

        public MenuOptionDTO(Store store, Menu menu, List<Option> options) {
            this.storeId = store.getId();
            this.storeName = store.getName();
            this.menuId = menu.getId();
            this.menuImgFilename = menu.getImgFilename();
            this.menuName = menu.getName();
            this.menuPrice = menu.getPrice();
            this.options = optionDTOList(options);
        }

        private List<OptionDTO> optionDTOList(List<Option> options) {
            List<OptionDTO> optionDTOList = options.stream().map(OptionDTO::new).toList();
            return optionDTOList;
        }

        @Data
        public class OptionDTO {
            private int id;
            private String name;
            private int price;
            private boolean isRequired;

            public OptionDTO(Option option) {
                this.id = option.getId();
                this.name = option.getName();
                this.price = option.getPrice();
                this.isRequired = option.isRequired();
            }
        }
    }

    // 주문내역 목록보기 DTO
    @Data
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
}
