package shop.project.pathorderserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 등록
    @PostMapping("/api/stores/{storeId}/reviews")
    public ResponseEntity<?> addReview(@RequestBody ReviewRequest.AddDTO reqDTO) {
        ReviewResponse.AddDTO respDTO = reviewService.createReview(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 내 리뷰 보기
    @GetMapping("/api/users/{userId}/reviews")
    public ResponseEntity<?> myReviewList(@PathVariable int userId) {
        ReviewResponse.MyReviewListDTO respDTO = reviewService.myReviewList(userId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
