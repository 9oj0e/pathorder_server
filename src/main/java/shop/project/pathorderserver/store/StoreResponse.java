package shop.project.pathorderserver.store;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StoreResponse {

    @Data
    public static class ListingsDTO {
        private int id;
        private String imgSrc;
        private String name;
        // TODO: 주소 좌표로 거리를 계산할 수 있을 것인가? latitude, longitude 속성 활용? (지도 api가 알아서 해주길 바람..)
        private int distance;
        private int likeCount;

        public ListingsDTO(Store store) {
            this.id = store.getId();
            this.imgSrc = store.getImgSrc();
            this.name = store.getName();
            this.distance = 163;
            this.likeCount = 181;
        }
    }
}
