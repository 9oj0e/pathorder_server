package shop.project.pathorderserver.review;

import lombok.Data;

public class ReviewRequest {

    // 리뷰 등록
    @Data
    public static class AddDTO {
        private int userId;
        private int storeId;
        private String content;
        private String imgFilename;
    }
}
