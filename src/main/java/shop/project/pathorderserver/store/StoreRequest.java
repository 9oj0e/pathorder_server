package shop.project.pathorderserver.store;

import lombok.Data;
import shop.project.pathorderserver.order.OrderStatus;

public class StoreRequest {
    @Data // 매장 관리자 등록하기
    public static class JoinDTO {
        // 회원 정보
        private String username;
        private String password;
        // 사업자 정보
        private String ownerName;
        private String ownerTel;
        private String ownerEmail;
        private String bizNum;
        // 매장 정보
        private String imgFilename;
        private String name;
        private String tel;
        private String intro;
        private String openingTime;
        private String closingTime;
        private String closedDay;
        private String address;
    }

    @Data // 매장 관리자 로그인
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data // 매장 관리자 정보 수정
    public static class UpdateDTO {
        // 회원 정보
        private String username;
        private String password;
        // 사업자 정보
        private String ownerName;
        private String ownerTel;
        private String ownerEmail;
        private String bizNum;
        // 매장 정보
        private String imgFilename;
        private String name;
        private String tel;
        private String intro;
        private String openingTime;
        private String closingTime;
        private String closedDay;
        private String address;
    }

    @Data // TODO: 매장 관리자 - 메뉴 등록
    public static class CreateMenuDTO {
    }

    @Data // TODO: 매장 관리자 - 메뉴 수정
    public static class UpdateMenuDTO {
    }

    @Data // TODO: 매장 관리자 - 메뉴 옵션 등록
    public static class CreateMenuOptionDTO {
    }

    @Data // TODO: 매장 관리자 - 메뉴 옵션 수정
    public static class UpdateMenuOptionDTO {
    }

    @Data // TODO: 매장 관리자 - 주문 업데이트
    public static class UpdateOrderDTO {
        private OrderStatus status;
    }
}
