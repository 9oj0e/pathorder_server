package shop.project.pathorderserver.like;

import lombok.Data;
import shop.project.pathorderserver.store.Store;

public class LikeResponse {
    @Data
    public static class LikeListDTO{
        private int id;
        private int storeId;
        private String storeImgFilename;
        private String storeName;
        private int distance;
        private boolean isLike;

        public LikeListDTO(Like like, Store store, boolean isLike) {
            this.id = like.getId();
            this.storeId = store.getId();
            this.storeImgFilename = store.getImgFilename();
            this.storeName = store.getName();
            // TODO: 거리는 지도 api로 경도위도값 받아온 후에 적용할 것이다.
            this.distance = 163;
            this.isLike = isLike;
        }
    }
}
