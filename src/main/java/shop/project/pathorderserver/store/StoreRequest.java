package shop.project.pathorderserver.store;

import lombok.Data;

public class StoreRequest {
    @Data
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
        private String name;
        private String tel;
        private String intro;
        private String openingTime;
        private String closingTime;
        private String closedDay;
        private String address;
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class UpdateDTO {
        // TODO: 매장 정보 수정
    }

    @Data
    public static class CreateMenuDTO {
        // TODO: 매장 메뉴 등록
    }

    @Data
    public static class UpdateMenuDTO {
        // TODO: 매장 메뉴 수정
    }

    @Data
    public static class CreateMenuOptionDTO {
        // TODO: 매장 메뉴 옵션 등록
    }

    @Data
    public static class UpdateMenuOptionDTO {
        // TODO: 매장 메뉴 옵션 수정
    }

    @Data
    public static class UpdateOrderDTO {
        // TODO: 주문 수정 (매장 측: 주문 상태 변경)
    }
}
