package shop.project.pathorderserver.store;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
@NoArgsConstructor
@Data
@Entity
@Table(name = "store_tb")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 회원 정보
    private String username; // 로그인 아이디
    private String password;
    @ColumnDefault("true")
    private boolean status; // 계정 상태(0 : 비활성, 1 : 활성)
    // 사업자 정보
    private String ownerName; // 사업자 이름
    private String ownerTel;
    private String ownerEmail;
    private String bizNum; // 사업자 등록 번호
    // 매장 정보
    private String name;
    private String tel; // 매장 번호
    private String intro; // 매장 소개
    @ColumnDefault("'default/store.jpeg'")
    private String imgFilename;
    private String openingTime; // 개점 시간
    private String closingTime; // 폐점 시간
    private String closedDay; // 휴무일
    private String address; // 주소
    @Column(nullable = true)
    private Double latitude; // 주소 좌표 위도
    @Column(nullable = true)
    private Double longitude; // 주소 좌표 경도

    @CreationTimestamp
    private Timestamp registeredAt; // 가입일

    public Store(StoreRequest.매장등록 reqDTO) {
        this.username = reqDTO.getUsername();
        this.password = reqDTO.getPassword();
        this.ownerName = reqDTO.getOwnerName();
        this.ownerTel = reqDTO.getOwnerTel();
        this.ownerEmail = reqDTO.getOwnerEmail();
        this.bizNum = reqDTO.getBizNum();
        this.name = reqDTO.getName();
        this.tel = reqDTO.getTel();
        this.intro = reqDTO.getIntro();
        this.openingTime = reqDTO.getOpeningTime();
        this.closingTime = reqDTO.getClosingTime();
        this.closedDay = reqDTO.getClosedDay();
        this.address = reqDTO.getAddress();
    }
}
