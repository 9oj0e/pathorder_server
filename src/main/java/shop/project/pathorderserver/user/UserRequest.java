package shop.project.pathorderserver.user;

import lombok.Data;

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
}
