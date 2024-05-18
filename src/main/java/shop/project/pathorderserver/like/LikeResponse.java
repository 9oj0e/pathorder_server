package shop.project.pathorderserver.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import shop.project.pathorderserver.store.Store;

public class LikeResponse {

    @Data
    @AllArgsConstructor
    public static class AddLikeDTO {
        private int userId;
        private int storeId;

        public AddLikeDTO(LikeRequest.AddLikeDTO reqDTO) {
            this.userId = reqDTO.getUserId();
            this.storeId = reqDTO.getStoreId();
        }
    }

    @Data
    @AllArgsConstructor
    public static class RemoveLikeDTO {
        private int userId;
        private int storeId;
    }

    @Data
    @Builder
    public static class LikeListDTO{
        private int id;
        private int storeId;
        private String storeImgFilename;
        private String storeName;
        private String distance;
        private boolean isLike;
        private Double latitude;
        private Double longitude;
    }

    @Data
    @Builder
    public static class StoreLikeCountDTO {
        private int storeId;
        private int likeCount;
    }
}
