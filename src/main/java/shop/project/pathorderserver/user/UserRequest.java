package shop.project.pathorderserver.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class UserRequest {

    @Data // 회원 가입
    public static class JoinDTO {
        // 회원 정보
        private String username;
        private String password;
        private String nickname; // 회원 별명
        // 개인 정보
        private String name;
        private String tel;
        private String email;
    }

    @Data // 로그인
    public static class LoginDTO {
        private String username;
        private String password; // TODO: 암호화

    }

    @Data // 회원 정보 수정
    public static class UpdateDTO {
        // 회원 정보
        // private String password; // view 기준
        private String nickname;
        // 개인 정보
        private String tel;
        private String email;
    }

    @Data
    public static class ImgDTO {
        private String username;
        private String encodedImg;
    }


    @Data
    public static class OrderDTO {
        private int storeId;
        private String storeName;
        private int customerId;
        private String customerNickname;
        private String request;
        private List<OrderMenuDTO> orderMenuList;

        @Data
        public static class OrderMenuDTO {
            // private int menuId; 참조한 메뉴 id?
            private String name;
            private int price;
            private int qty;
            private List<OrderMenuOptionDTO> orderMenuOptionList = new ArrayList<>();
        }

        @Data
        public static class OrderMenuOptionDTO {
            private String name;
            private int price;
        }
    }
}
