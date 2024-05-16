package shop.project.pathorderserver.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;

    // 좋아요 추가
    @PostMapping("/api/users/{userId}/likes")
    public ResponseEntity<?> addLike(@PathVariable int userId, @RequestBody LikeRequest.AddLikeDTO reqDTO) {
        // 요청 DTO에 userId를 설정합니다.
        reqDTO.setUserId(userId);

        LikeResponse.LikeListDTO respDTO = likeService.addLike(reqDTO);
        System.out.println("우왓");
        System.out.println(respDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 좋아요 삭제
    @DeleteMapping("/api/users/{userId}/likes")
    public ResponseEntity<?> removeLike(@PathVariable int userId, @RequestBody LikeRequest.RemoveLikeDTO reqDTO) {
        reqDTO.setUserId(userId);

        likeService.removeLike(reqDTO);
        LikeResponse.RemoveLikeDTO respDTO = new LikeResponse.RemoveLikeDTO(userId, reqDTO.getStoreId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 특정 사용자의 좋아요 목록 조회
    @GetMapping("/api/users/{userId}/likes")
    public ResponseEntity<?> getUserLikes(@PathVariable int userId) {
        List<LikeResponse.LikeListDTO> respDTO = likeService.getUserLikes(userId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

//    // 특정 카페의 좋아요 수 조회
//    @GetMapping("/api/stores/{storeId}/likes/count")
//    public ResponseEntity<?> getStoreLikeCount(@PathVariable int storeId) {
//        LikeResponse.StoreLikeCountDTO respDTO = likeService.getStoreLikeCount(storeId);
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }
}
