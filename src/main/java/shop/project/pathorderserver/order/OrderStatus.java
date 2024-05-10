package shop.project.pathorderserver.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("접수대기"),
    DENIED("주문거절"),
    CONFIRMED("주문완료"),
    PREPARING("조리중"),
    PREPARED("조리완료"),
    SERVED("수령완료");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }
}