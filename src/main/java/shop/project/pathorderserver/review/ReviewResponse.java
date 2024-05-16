package shop.project.pathorderserver.review;

import lombok.Builder;
import lombok.Data;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.user.User;

public class ReviewResponse {

    // 리뷰 등록
    @Data
    public static class AddDTO {
        private int userId;
        private int storeId;
        private String content;
        private String imgFilename;

        @Builder
        public AddDTO(Review review, User user, Store store) {
            this.userId = user.getId();
            this.storeId = store.getId();
            this.content = review.getContent();
            this.imgFilename = review.getImgFilename();
        }
    }
}
