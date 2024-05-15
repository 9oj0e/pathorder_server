package shop.project.pathorderserver.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver.store.Store;
import shop.project.pathorderserver.store.StoreRepository;
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

    @Transactional
    public LikeResponse.LikeListDTO addLike(LikeRequest.AddLikeDTO reqDTO) {
        User user = userRepository.findById(reqDTO.getUserId()).orElseThrow(() -> new Exception404("해당하는 유저가 없습니다."));
        Store store = storeRepository.findById(reqDTO.getStoreId()).orElseThrow(() -> new Exception404("해당하는 가게가 없습니다."));

        Like like = new Like();
        like.setCustomer(user);
        like.setStore(store);
        likeRepository.save(like);

        return LikeResponse.LikeListDTO.builder()
                .id(like.getId())
                .storeId(store.getId())
                .storeImgFilename(store.getImgFilename())
                .storeName(store.getName())
                .distance(163) // TODO: 실제 거리 계산 로직 추가
                .isLike(true)
                .build();
    }

    @Transactional
    public void removeLike(LikeRequest.RemoveLikeDTO reqDTO) {
        Optional<Like> like = likeRepository.findByCustomerIdAndStoreId(reqDTO.getUserId(), reqDTO.getStoreId());
        like.ifPresent(likeRepository::delete);
    }

    public List<LikeResponse.LikeListDTO> getUserLikes(int userId) {
        List<Object[]> results = likeRepository.findLikesByUserId(userId);
        return results.stream()
                .map(result -> LikeResponse.LikeListDTO.builder()
                        .id((Integer) result[0])
                        .storeId((Integer) result[1])
                        .storeImgFilename((String) result[2])
                        .storeName((String) result[3])
                        .distance(163) // TODO: 실제 거리 계산 로직 추가
                        .isLike(true)
                        .build())
                .toList();
    }

    public LikeResponse.StoreLikeCountDTO getStoreLikeCount(int storeId) {
        // 특정 카페의 좋아요 수 조회
        int count = likeRepository.countByStoreId(storeId);
        return LikeResponse.StoreLikeCountDTO.builder()
                .storeId(storeId)
                .likeCount(count)
                .build();
    }
}
