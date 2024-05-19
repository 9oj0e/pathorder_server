package shop.project.pathorderserver.review;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;
import shop.project.pathorderserver.user.SessionUser;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final HttpSession session;

    // 리뷰 등록
    @PostMapping("/api/stores/{storeId}/reviews")
    public ResponseEntity<?> addReview(@PathVariable int storeId, @RequestBody @Valid ReviewRequest.AddDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reviewService.addReview(reqDTO, storeId, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

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
