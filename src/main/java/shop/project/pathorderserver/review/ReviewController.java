package shop.project.pathorderserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.project.pathorderserver._core.utils.ApiUtil;

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
}
