package shop.project.pathorderserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    // 내 리뷰 보기
    @GetMapping("/api/users/{userId}/reviews")
    public ResponseEntity<?> myReviewList(@PathVariable int userId) {
        ReviewResponse.MyReviewListDTO respDTO = reviewService.myReviewList(userId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 매장 리뷰 보기
    @GetMapping("/api/stores/{storeId}/reviews")
    public ResponseEntity<?> storeReviewList(@PathVariable int storeId) {
        ReviewResponse.StoreReviewListDTO respDTO = reviewService.storeReviewList(storeId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
