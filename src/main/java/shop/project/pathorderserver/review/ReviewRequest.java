package shop.project.pathorderserver.review;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.user.User;

public class ReviewRequest {
    // 리뷰 등록
    @Data
    public static class AddDTO {
        @NotBlank(message = "내용을 입력해주세요.")
        @Size(min = 5, message = "내용은 5자 이상 입력해주세요.")
        private String content;
        private String encodedData;
    }
}
