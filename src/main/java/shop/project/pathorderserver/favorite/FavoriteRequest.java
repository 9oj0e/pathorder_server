package shop.project.pathorderserver.favorite;

import lombok.Data;
import shop.project.pathorderserver.store.Store;

public class FavoriteRequest {
    @Data
    public static class FavoriteListDTO{
        private int storeId;
        private String storeName;
        private String storeAddress;
        private String storeImgFilename;
        private boolean isFavorite;

        public FavoriteListDTO(Store store, boolean isFavorite) {
            this.storeId = store.getId();
            this.storeName = store.getName();
            this.storeAddress = store.getAddress();
            this.storeImgFilename = store.getImgFilename();
            this.isFavorite = isFavorite;
        }
    }
}
