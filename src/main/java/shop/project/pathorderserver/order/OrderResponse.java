package shop.project.pathorderserver.order;

import lombok.Data;
import shop.project.pathorderserver._core.utils.FormatUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    /*
    @Data // 주문내역 상세보기
    public static class OrderDTO {
        private int storeId;
        private String storeName;
        private String storeTel;
        private int customerId;
        private String customerNickname;
        private Timestamp orderTime;
        private String request;
        private List<OrderResponse.OrderDTO.OrderMenuDTO> orderMenuList;
        private int totalPrice;

        public OrderDTO(Order order, List<OrderMenu> orderMenus) {
            this.storeName = order.getStoreName();
            this.storeTel = order.getStore().getTel();
            this.orderTime = order.getCreatedAt();
            this.request = order.getRequest();
            this.totalPrice = order.getTotalPrice();
            this.orderMenuList = orderMenus.stream().map(OrderResponse.OrderDTO.OrderMenuDTO::new).toList();
        }

        @Data
        private class OrderMenuDTO {
            private int menuId;
            private String menuName;
            private int menuPrice;
            private int totalPrice;
            private List<OrderResponse.OrderDTO.OrderMenuDTO.OrderMenuOptionDTO> orderMenuOptionList;

            public OrderMenuDTO(OrderMenu orderMenu) {
                this.menuId = orderMenu.getId();
                this.menuName = orderMenu.getName();
                this.menuPrice = orderMenu.getPrice();
                this.totalPrice = orderMenu.getPrice();
                this.orderMenuOptionList = orderMenu.getOrderMenuOption().stream().map(OrderMenuOptionDTO::new).toList();
                List<Integer> optionPriceList = orderMenuOptionList.stream().map(orderMenuOptionDTO -> orderMenuOptionDTO.getPrice()).toList();
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
            *//*
            public void setTotalPrice() { // 총액 계산
                this.totalPrice += menuPrice;
                List<Integer> optionPriceList = menuOptionList.stream().map(orderOptionDTO -> orderOptionDTO.getPrice()).toList();
                for (int optionPrice : optionPriceList) {
                    totalPrice += optionPrice;
                }
            }
            *//*
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
    */
    @Data
    public static class OrderDTO {
        private int storeId;
        private String storeName;
        private int customerId;
        private String customerNickname;
        private String request;
        private int totalPrice;
        private OrderStatus status;
        private List<OrderMenuDTO> orderMenuList;

        public OrderDTO(Order order, List<OrderMenu> orderMenus, List<OrderMenuOption> orderMenuOptions) {
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
            private int qty;
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
            }
        }
        // TODO: 수정된 주문 내용 (매장 측: 주문 상태 변경)
    }
}