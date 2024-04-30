package shop.project.pathorderserver.store;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

public class StoreRequest {
    @Data
    public static class 매장등록 { // TODO: 매장 등록
        private String username;
        private String password;
        private String ownerName;
        private String ownerTel;
        private String ownerEmail;
        private String bizNum;
        private String name;
        private String tel;
        private String intro;
        private String openingTime;
        private String closingTime;
        private String closedDay;
        private String address;
    }

    @Data
    public static class 로그인 {
        private String username;
        private String password;
    }
    // TODO: 매장 정보 수정
    // TODO: 매장 메뉴 등록
    // TODO: 매장 메뉴 수정
    // TODO: 매장 메뉴 삭제
    // TODO: 매장 메뉴옵션 등록
    // TODO: 매장 메뉴옵션 수정
    // TODO: 매장 메뉴옵션 삭제
    // TODO: 주문 수정 (매장 측: 주문 상태 변경)
}
