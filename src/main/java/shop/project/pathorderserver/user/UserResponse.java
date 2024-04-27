package shop.project.pathorderserver.user;

import lombok.Data;

public class UserResponse {

    @Data // 회원가입 완료시
    public static class JoinDTO {
        // private int id;
        private String username;
        private String nickname;
    }

    @Data // 로그인시
    public static class LoginDTO {
        private int id;
        private String nickname; // 닉네임 응답
    }

    @Data // 회원 정보 조회시
    public static class UserDTO {
        // 회원 정보
        private int id;
        private String nickname;
        // 개인 정보
        private String email;
        private String tel;
        // 사진
        private String imgFilePath;
    }

    @Data // 회원 정보 수정시
    public static class UpdateDTO {
        // 회원 정보
        private int id;
        private String nickname;
        // 개인 정보
        private String email;
        private String tel;
    }
    @Data // 사진 수정시
    public static class ImgDTO {
        // 사진
        private String newImgFilePath;
    }
}
