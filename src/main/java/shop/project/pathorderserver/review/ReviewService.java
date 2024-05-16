package shop.project.pathorderserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;
import shop.project.pathorderserver.user.User;
import shop.project.pathorderserver.user.UserRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ReviewResponse.AddDTO createReview(ReviewRequest.AddDTO reqDTO) {
        User user = userRepository.findById(reqDTO.getUserId())
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        Store store = storeRepository.findById(reqDTO.getStoreId())
                .orElseThrow(() -> new Exception404("찾을 수 없는 매장입니다."));
        Review review = new Review(reqDTO, user, store);
        reviewRepository.save(review);

        return ReviewResponse.AddDTO.builder()
                .review(review)
                .user(user)
                .store(store)
                .build();
    }
}
