package shop.project.pathorderserver.store;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import shop.project.pathorderserver.order.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class StoreRequest {
    @Data // 매장 관리자 등록하기
    public static class JoinDTO {
        // 회원 정보
        @NotBlank(message = "로그인 아이디는 필수 입력값입니다.")
        @Size(min = 3, max = 12, message = "로그인 아이디는 3자 이상 12자 이하로 입력해주세요.")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "로그인 아이디는 영어와 숫자만 사용할 수 있습니다.")
        private String username;

        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        @Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하로 입력해주세요.")
        @Pattern(regexp = "^\\S+$", message = "비밀번호는 공백을 포함할 수 없습니다.")
        private String password;

        @NotBlank(message = "사업자 이름은 필수 입력값입니다.")
        private String ownerName;

        @NotBlank(message = "전화번호는 필수 입력값입니다.")
        @Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "전화번호에는 숫자만 입력해주세요.")
        private String ownerTel;

        @NotBlank(message = "이메일은 필수 입력값입니다.")
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        private String ownerEmail;

        @NotBlank(message = "사업자 등록 번호는 필수 입력값입니다.")
        @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "사업자 등록 번호 형식이 올바르지 않습니다. (예: 123-12-12345)")
        private String bizNum;

        @NotBlank(message = "매장 이름은 필수 입력값입니다.")
        private String name;

        @Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "매장 전화번호는 숫자만 입력해주세요.")
        private String tel;
        private String address;
        private Double latitude;
        private Double longitude;
    }

    @Data // 매장 관리자 로그인
    public static class LoginDTO {
        @NotBlank(message = "로그인 아이디는 필수 입력값입니다.")
        private String username;

        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        private String password;
    }

    @Data // 매장 관리자 정보 수정
    public static class UpdateDTO {
        @Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하로 입력해주세요.")
        @Pattern(regexp = "^\\S+$", message = "비밀번호는 공백을 포함할 수 없습니다.")
        private String password;

        @NotBlank(message = "사업자 이름은 필수 입력값입니다.")
        private String ownerName;

        @NotBlank(message = "전화번호는 필수 입력값입니다.")
        @Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "전화번호에는 숫자만 입력해주세요.")
        private String ownerTel;

        @NotBlank(message = "이메일은 필수 입력값입니다.")
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        private String ownerEmail;

        @NotBlank(message = "사업자 등록 번호는 필수 입력값입니다.")
        @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "사업자 등록 번호 형식이 올바르지 않습니다. (예: 123-12-12345)")
        private String bizNum;

        @NotBlank(message = "매장 이름은 필수 입력값입니다.")
        private String name;

        @Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "전화번호에는 숫자만 입력해주세요.")
        private String tel;

        private String intro;
        private String openingTime;
        private String closingTime;
        private String closedDay;
        private String address;
        private Double latitude;
        private Double longitude;
    }

    @Data
    public static class CreateMenuDTO {
        private int price;
        private String category;
        @NotBlank(message = "메뉴 이름은 필수 입력값입니다.")
        private String name;
        private MultipartFile imgFile;
        private String description;
    }

    @Data // 매장 관리자 - 메뉴 수정
    public static class UpdateMenuDTO {
        private int price;
        private String category;
        @NotBlank(message = "메뉴 이름은 필수 입력값입니다.")
        private String name;
        private String encodedFile;
        private String description;
        private List<MenuOptionDTO> menuOptions = new ArrayList<>();

        @Data
        public static class MenuOptionDTO {
            private int price;
            private String name;
            private boolean isRequired;
        }
    }

    @Data // TODO: 매장 관리자 - 주문 업데이트
    public static class UpdateOrderDTO {
        private OrderStatus status;
    }
}
