package shop.project.pathorderserver.review;

import lombok.Builder;
import lombok.Data;
import shop.project.pathorderserver._core.utils.FormatUtil;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.user.User;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReviewResponse {
    // 내 리뷰 보기
    @Data
    public static class MyReviewListDTO {
        private List<ReviewDTO> reviewList;

        public MyReviewListDTO(List<Review> reviewList) {
            this.reviewList = reviewList.stream().map(ReviewDTO::new).toList();
        }

        @Data
        public static class ReviewDTO {
            private int userId;
            private String nickname;
            private String usersImgFilename;
            private int reviewId;
            private String content;
            private String imgFilename;
            private Timestamp createdAt;

            public ReviewDTO(Review review) {
                this.userId = review.getUser().getId();
                this.nickname = review.getUser().getNickname();
                this.usersImgFilename = review.getUser().getImgFilename();
                this.reviewId = review.getId();
                this.content = review.getContent();
                this.imgFilename = review.getImgFilename();
                this.createdAt = review.getCreatedAt();
            }

            public String getCreatedAt() {
                return FormatUtil.dateFormatter(createdAt);
            }
        }
    }

    // 매장 리뷰 보기
    @Data
    public static class StoreReviewListDTO {
        private List<ReviewDTO> reviewList;

        public StoreReviewListDTO(List<Review> reviewList) {
            this.reviewList = reviewList.stream().map(ReviewDTO::new).toList();
        }

        @Data
        public static class ReviewDTO {
            private int userId;
            private String nickname;
            private String usersImgFilename;
            private int reviewId;
            private String content;
            private String reviewsImgFilename;
            private Timestamp createdAt;

            public ReviewDTO(Review review) {
                this.userId = review.getUser().getId();
                this.nickname = review.getUser().getNickname();
                this.usersImgFilename = review.getUser().getImgFilename();
                this.reviewId = review.getId();
                this.content = review.getContent();
                this.reviewsImgFilename = review.getImgFilename();
                this.createdAt = review.getCreatedAt();
            }

            public String getCreatedAt() {
                return FormatUtil.dateFormatter(createdAt);
            }
        }
    }
}
