package shop.project.pathorderserver.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LikeRequest {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddLikeDTO {
        private int userId;
        private int storeId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RemoveLikeDTO {
        private int userId;
        private int storeId;
    }
}
