package shop.project.pathorderserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.App403;
import shop.project.pathorderserver._core.errors.exception.App404;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;
import shop.project.pathorderserver.user.User;
import shop.project.pathorderserver.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 리뷰 등록
    @Transactional
    public void addReview(ReviewRequest.AddDTO reqDTO, int storeId, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new App403("권한 없는 유저입니다."));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new App404("찾을 수 없는 매장입니다."));

        System.out.println("reqDTO: " + reqDTO);


        Review review = new Review(reqDTO, user, store);
        reviewRepository.save(review);
    }

    // 내 리뷰 보기
    public ReviewResponse.MyReviewListDTO myReviewList(int userId) {
        List<Review> reviewList = reviewRepository.findByUserId(userId)
                .orElseThrow(() -> new App403("리뷰를 확인할 권한이 없습니다."));

        return new ReviewResponse.MyReviewListDTO(reviewList);
    }

    // 매장 리뷰 보기
    public ReviewResponse.StoreReviewListDTO storeReviewList(int storeId) {
        List<Review> reviewList = reviewRepository.findByStoreId(storeId)
                .orElseThrow(() -> new App404("찾을 수 없는 매장입니다."));

        return new ReviewResponse.StoreReviewListDTO(reviewList);
    }
}
