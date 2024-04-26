package shop.project.pathorderserver.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {

    }

    @Data
    public static class LoginDTO {

    }

    @Data
    public static class UpdateDTO {

    }

    @Data
    public static class ImgDTO {
        private String username;
        private String encodedImg;
    }
}
