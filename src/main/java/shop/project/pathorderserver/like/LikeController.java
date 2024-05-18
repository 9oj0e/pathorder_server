package shop.project.pathorderserver.like;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.project.pathorderserver._core.utils.ApiUtil;
import shop.project.pathorderserver.user.SessionUser;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;
    private final HttpSession session;

    // 좋아요 추가
    @PostMapping("/api/users/{userId}/likes")
    public ResponseEntity<?> addLike(@PathVariable int userId, @RequestBody LikeRequest.AddLikeDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());
        LikeResponse.AddLikeDTO respDTO = likeService.addLike(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 좋아요 삭제
    @DeleteMapping("/api/users/{userId}/likes")
    public ResponseEntity<?> removeLike(@PathVariable int userId, @RequestBody LikeRequest.RemoveLikeDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());

        likeService.removeLike(reqDTO);
        LikeResponse.RemoveLikeDTO respDTO = new LikeResponse.RemoveLikeDTO(sessionUser.getId(), reqDTO.getStoreId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 특정 사용자의 좋아요 목록 조회
    @GetMapping("/api/users/{userId}/likes")
    public ResponseEntity<?> getUserLikes(@PathVariable int userId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<LikeResponse.LikeListDTO> respDTO = likeService.getUserLikes(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
