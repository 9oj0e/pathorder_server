package shop.project.pathorderserver.like;

import lombok.AllArgsConstructor;
import lombok.Data;

public class LikeRequest {
    @Data
    @AllArgsConstructor
    public static class AddLikeDTO {
        private int userId;
        private int storeId;
    }

    @Data
    @AllArgsConstructor
    public static class RemoveLikeDTO {
        private int userId;
        private int storeId;
    }
}
