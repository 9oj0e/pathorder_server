package shop.project.pathorderserver.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        // 사용자와 카페를 데이터베이스에서 조회
        User user = userRepository.findById(reqDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Store store = storeRepository.findById(reqDTO.getStoreId()).orElseThrow(() -> new RuntimeException("Store not found"));

        // Like 엔티티 생성 및 저장
        Like like = new Like();
        like.setCustomer(user);
        like.setStore(store);
        likeRepository.save(like);

        // 반환할 DTO 생성
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
        // 사용자와 카페 간의 좋아요 관계를 조회
        Optional<Like> like = likeRepository.findByCustomerIdAndStoreId(reqDTO.getUserId(), reqDTO.getStoreId());
        like.ifPresent(likeRepository::delete);
    }

    public List<LikeResponse.LikeListDTO> getUserLikes(int userId) {
        // JPQL 쿼리를 사용하여 특정 사용자의 좋아요 목록을 조회하고 빌더 패턴으로 DTO로 변환
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
                .collect(Collectors.toList());
    }

    public LikeResponse.StoreLikeCountDTO getStoreLikeCount(int storeId) {
        // 특정 카페의 좋아요 수 조회
        int count = likeRepository.countByStoreId(storeId);
        return LikeResponse.StoreLikeCountDTO.builder()
                .storeId(storeId)
                .likeCount((int) count)
                .build();
    }
}
