package shop.project.pathorderserver.store;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "store_tb")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // 회원 정보
    private String username;
    private String password;
    private boolean status; // 계정 상태 (0 : 비활성, 1 : 활성)
    private Timestamp registeredAt; // 가입일
    // 사업자 정보
    private String ownerName; // 사업자 이름
    private String ownerTel;
    private String ownerEmail;
    private String bizNum; // 사업자 등록 번호
    // 매장 정보
    private String name;
    private String tel; // 매장 번호
    private String intro; // 매장 소개
    private String imgSrc;
    private Timestamp openingTime;
    private Timestamp closingTime;
    private String closedDay; // 휴무일
    private String address; // 주소
    private double latitude; // 주소 좌표 위도
    private double longitude; // 주소 좌표 경도
}
