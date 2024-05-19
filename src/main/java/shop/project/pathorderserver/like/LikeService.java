package shop.project.pathorderserver.like;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver._core.errors.exception.Exception400;
import shop.project.pathorderserver._core.utils.DistanceUtil;
import shop.project.pathorderserver.review.ReviewRepository;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;
import shop.project.pathorderserver.user.SessionUser;
import shop.project.pathorderserver.store.StoreService;
import shop.project.pathorderserver.user.User;
import shop.project.pathorderserver.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikeService {
    final private UserRepository userRepository;
    final private StoreRepository storeRepository;
    final private LikeRepository likeRepository;
    final private HttpSession session;
    private final ReviewRepository reviewRepository;

    @Transactional
    public LikeResponse.AddLikeDTO addLike(LikeRequest.AddLikeDTO reqDTO) {
        // 이미 좋아요가 존재하는지 확인
        if (likeRepository.existsByCustomerIdAndStoreId(reqDTO.getUserId(), reqDTO.getStoreId())) {
            throw new Exception400("이미 좋아요가 되어 있는 매장입니다.");
        }

        User user = userRepository.findById(reqDTO.getUserId()).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        Store store = storeRepository.findById(reqDTO.getStoreId()).orElseThrow(() -> new Exception404("해당하는 매장을 찾을 수 없습니다."));

        Like like = new Like();
        like.setCustomer(user);
        like.setStore(store);
        likeRepository.save(like);

        return new LikeResponse.AddLikeDTO(reqDTO);
    }

    @Transactional
    public void removeLike(LikeRequest.RemoveLikeDTO reqDTO) {
        Optional<Like> like = likeRepository.findByCustomerIdAndStoreId(reqDTO.getUserId(), reqDTO.getStoreId());
        like.ifPresent(likeRepository::delete);
    }

    public List<LikeResponse.LikeListDTO> getUserLikes(int userId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        double customerLatitude = sessionUser.getLatitude();
        double customerLongitude = sessionUser.getLongitude();

        List<Object[]> results = likeRepository.findLikesByUserId(userId);
        return results.stream()
                .map(result -> {
                    int storeId = (Integer) result[1];
                    int likeCount = getStoreLikeCount(storeId);
                    int reviewCount = getReviewCount(storeId);
                    Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception404("해당하는 매장을 찾을 수 없습니다."));
                    int distance = DistanceUtil.calculateDistance(customerLatitude, customerLongitude, store.getLatitude(), store.getLongitude());

                    return LikeResponse.LikeListDTO.builder()
                            .id((Integer) result[0])
                            .storeId(storeId)
                            .storeImgFilename((String) result[2])
                            .storeName((String) result[3])
                            .distance(distance)
                            .isLike(true)
                            .latitude((Double) result[4]) // 위도 설정
                            .longitude((Double) result[5]) // 경도 설정
                            .likeCount(likeCount)
                            .reviewCount(reviewCount)
                            .build();
                })
                .toList();
    }

    public boolean isUserLikedStore(int userId, int storeId) {
        return likeRepository.existsByCustomerIdAndStoreId(userId, storeId);
    }

    public int getStoreLikeCount(int storeId) {
        return likeRepository.countByStoreId(storeId);
    }

    public int getReviewCount(int storeId) {
        return reviewRepository.findReviewCountByStoreId(storeId);
    }
}
