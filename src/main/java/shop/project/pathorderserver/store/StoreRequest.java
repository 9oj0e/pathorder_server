package shop.project.pathorderserver.store;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
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
        private int price; // 메뉴 하나의 가격
        private String category; // 각 메뉴가 포함되는 카테고리, 점주가 직접 작성
        private String name; // 메뉴 이름
        private MultipartFile imgFile;
        private String description; // 메뉴 설명
    }

    @Data // 매장 관리자 - 메뉴 수정
    public static class UpdateMenuDTO {
        private int id;
        private int price; // 메뉴 하나의 가격
        private String category; // 각 메뉴가 포함되는 카테고리, 점주가 직접 작성
        private String name; // 메뉴 이름
        private MultipartFile imgFile;
        private String description; // 메뉴 설명
    }

    @Data // TODO: 매장 관리자 - 메뉴 옵션 등록
    public static class CreateMenuOptionDTO {
        // 옵션 정보
        private int price;
        private String name;
        private boolean isRequired;
    }

    @Data // TODO: 매장 관리자 - 메뉴 옵션 수정
    public static class UpdateMenuOptionDTO {
        private int price;
        private String name;
        private boolean isRequired;
    }

    @Data // TODO: 매장 관리자 - 주문 업데이트
    public static class UpdateOrderDTO {
        private OrderStatus status;
    }
}
