package shop.project.pathorderserver.review;

import lombok.Builder;
import lombok.Data;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.user.User;

import java.sql.Timestamp;
import java.util.List;

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

    // 내 리뷰 보기
    @Data
    public static class MyReviewListDTO {
        private List<ReviewDTO> reviewList;

        public MyReviewListDTO(List<Review> reviewList) {
            this.reviewList = reviewList.stream().map((Review review) -> new ReviewDTO(review.getUser(), review)).toList();
        }

        @Data
        public static class ReviewDTO {
            private User user;
            private String content;
            private String imgFilename;
            private Timestamp createdAt;

            public ReviewDTO(User user, Review review) {
                this.user = user;
                this.content = review.getContent();
                this.imgFilename = review.getImgFilename();
                this.createdAt = review.getCreatedAt();
            }
        }
    }
}
