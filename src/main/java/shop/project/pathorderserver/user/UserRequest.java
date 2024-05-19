package shop.project.pathorderserver.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import shop.project.pathorderserver.order.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class UserRequest {

    @Data // 회원 가입
    public static class JoinDTO {
        @NotBlank(message = "아이디는 필수 항목입니다.")
        @Size(min = 3, max = 12, message = "아이디는 3-12자 사이여야 합니다.")
        @Pattern(regexp = "^[^\\s]+$", message = "아이디는 공백을 포함할 수 없습니다.")
        private String username;

        @NotBlank(message = "비밀번호는 필수 항목입니다.")
        @Size(min = 4, max = 20, message = "비밀번호는 4-20자 사이여야 합니다.")
        @Pattern(regexp = "^[^\\s]+$", message = "비밀번호는 공백을 포함할 수 없습니다.")
        private String password;

        @NotBlank(message = "닉네임은 필수 항목입니다.")
        @Size(min = 2, max = 4, message = "닉네임은 2-4자 사이여야 합니다.")
        @Pattern(regexp = "^[^\\s]+$", message = "닉네임은 공백을 포함할 수 없습니다.")
        private String nickname; // 회원 별명

        @NotBlank(message = "이름은 필수 항목입니다.")
        private String name;

        @NotBlank(message = "전화번호는 필수 항목입니다.")
        @Size(max = 11, message = "전화번호는 최대 11자까지 가능합니다.")
        @Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자만 포함할 수 있습니다.")
        private String tel;

        @NotBlank(message = "이메일은 필수 항목입니다.")
        @Email(message = "유효한 이메일 형식이어야 합니다.")
        private String email;
    }

    @Data // 로그인
    public static class LoginDTO {
        @NotBlank(message = "아이디는 필수 항목입니다.")
        private String username;

        @NotBlank(message = "비밀번호는 필수 항목입니다.")
        private String password; // TODO: 암호화
    }

    @Data // 회원 정보 수정
    public static class UpdateDTO {
        private String nickname;
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
        private OrderStatus status;
        private List<OrderMenuDTO> orderMenuList;

        @Data
        public static class OrderMenuDTO {
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
