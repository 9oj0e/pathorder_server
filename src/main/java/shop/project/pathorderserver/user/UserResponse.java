package shop.project.pathorderserver.user;

import lombok.Builder;
import lombok.Data;

public class UserResponse {

    @Data // 회원가입 완료시
    public static class JoinDTO {
        // private int id;
        private String username;
        private String nickname;

        @Builder
        public JoinDTO(String username, String nickname) {
            this.username = username;
            this.nickname = nickname;
        }
    }

    @Data // 로그인시
    public static class LoginDTO {
        private int id;
        private String nickname; // 닉네임 응답

        @Builder
        public LoginDTO(int id, String nickname) {
            this.id = id;
            this.nickname = nickname;
        }
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
        private String imgFilename;

        @Builder
        public UserDTO(int id, String nickname, String email, String tel, String imgFilename) {
            this.id = id;
            this.nickname = nickname;
            this.email = email;
            this.tel = tel;
            this.imgFilename = imgFilename;
        }
    }

    @Data // 회원 정보 수정시
    public static class UpdateDTO {
        // 회원 정보
        private int id;
        private String nickname;
        // 개인 정보
        private String email;
        private String tel;

        @Builder
        public UpdateDTO(int id, String nickname, String email, String tel) {
            this.id = id;
            this.nickname = nickname;
            this.email = email;
            this.tel = tel;
        }
    }
    @Data // 사진 수정시
    public static class ImgDTO {
        // 사진
        private String newImgFilePath;

        public ImgDTO(String newImgFilePath) {
            this.newImgFilePath = newImgFilePath;
        }
    }
}
