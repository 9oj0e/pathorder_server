package shop.project.pathorderserver.order;

public enum OrderStatus {
    접수대기,
    주문거절,
    주문완료, // 손님측에서 볼
    조리중,
    조리완료,
    수령완료
}
