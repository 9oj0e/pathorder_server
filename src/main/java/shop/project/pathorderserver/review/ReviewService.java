package shop.project.pathorderserver.review;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import shop.project.pathorderserver._core.errors.exception.Exception403;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;
import shop.project.pathorderserver.user.SessionUser;
import shop.project.pathorderserver.user.User;
import shop.project.pathorderserver.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    // 내 리뷰 보기
    public ReviewResponse.MyReviewListDTO myReviewList(int userId) {
        List<Review> reviewList = reviewRepository.findByUserId(userId)
                .orElseThrow(() -> new Exception403("리뷰를 확인할 권한이 없습니다."));

        return new ReviewResponse.MyReviewListDTO(reviewList);
    }

    // 매장 리뷰 보기
    public ReviewResponse.StoreReviewListDTO storeReviewList(int storeId) {
        List<Review> reviewList = reviewRepository.findByStoreId(storeId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));

        return new ReviewResponse.StoreReviewListDTO(reviewList);
    }
}
