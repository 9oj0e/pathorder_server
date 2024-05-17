package shop.project.pathorderserver.review;

import lombok.Data;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.user.User;

public class ReviewRequest {
    // 리뷰 등록
    @Data
    public static class AddDTO {
        private String content;
        private String encodedData;
    }
}
