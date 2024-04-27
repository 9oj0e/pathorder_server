package shop.project.pathorderserver.store;

import lombok.Data;

public class StoreResponse {

    @Data
    public static class ListingsDTO {
        private int id;
        private String filePath;
        private String name;
        // TODO: 주소 좌표로 거리를 계산할 수 있을 것인가? latitude, longitude 속성 활용? (지도 api가 알아서 해주길 바람..)
        private int distance;
        private int likeCount;

        public ListingsDTO(Store store) {
            this.id = store.getId();
            this.filePath = store.getFilePath();
            this.name = store.getName();
            this.distance = 163;
            this.likeCount = 181;
        }
    }

    @Data
    public static class DetailDTO {
        private int id;
        private String filePath;
        private String name;
        private String intro;
        private String openingTime;
        private String closingTime;
        private String closedDay;
        private String address;

        public DetailDTO(Store store) {
            this.id = store.getId();
            this.filePath = store.getFilePath();
            this.name = store.getName();
            this.intro = store.getIntro();
            this.openingTime = store.getOpeningTime();
            this.closingTime = store.getClosingTime();
            this.closedDay = store.getClosedDay();
            this.address = store.getAddress();
        }
    }

    @Data
    public static class BizInfoDTO {
        private String bizNum;

        public BizInfoDTO(Store store) {
            this.bizNum = store.getBizNum();
        }
    }
}
