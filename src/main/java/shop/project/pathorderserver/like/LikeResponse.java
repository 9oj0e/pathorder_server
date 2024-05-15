package shop.project.pathorderserver.like;

import lombok.Builder;
import lombok.Data;
import shop.project.pathorderserver.store.Store;

public class LikeResponse {
    @Data
    @Builder
    public static class LikeListDTO{
        private int id;
        private int storeId;
        private String storeImgFilename;
        private String storeName;
        private int distance;
        private boolean isLike;
    }

    @Data
    @Builder
    public static class StoreLikeCountDTO {
        private int storeId;
        private int likeCount;
    }
}
