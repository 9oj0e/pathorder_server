package shop.project.pathorderserver.like;

import lombok.Data;
import shop.project.pathorderserver.store.Store;

public class LikeResponse {
    @Data
    public static class LikeListDTO{
        private int storeId;
        private String storeName;
        private String storeAddress;
        private String storeImgFilename;
        private boolean isLike;

        public LikeListDTO(Store store, boolean isLike) {
            this.storeId = store.getId();
            this.storeName = store.getName();
            this.storeAddress = store.getAddress();
            this.storeImgFilename = store.getImgFilename();
            this.isLike = isLike;
        }
    }
}
