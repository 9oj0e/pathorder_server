package shop.project.pathorderserver.like;

import lombok.Data;

public class LikeRequest {
    @Data
    public static class AddLikeDTO {
        private int userId;
        private int storeId;
    }

    @Data
    public static class RemoveLikeDTO {
        private int userId;
        private int storeId;
    }
}
