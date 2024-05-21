package shop.project.pathorderserver.store;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import shop.project.pathorderserver._core.DefaultFile;
import shop.project.pathorderserver._core.utils.FileUtil;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@DynamicInsert
@Entity
@Table(name = "store_tb")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username; // 로그인 아이디
    private String password;

    @ColumnDefault("true")
    private boolean status; // 계정 상태(0 : 비활성, 1 : 활성)
    private String ownerName; // 사업자 이름
    private String ownerTel;
    private String ownerEmail;
    private String bizNum; // 사업자 등록 번호
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

    public Store(StoreRequest.JoinDTO reqDTO) {
        this.username = reqDTO.getUsername();
        this.password = reqDTO.getPassword();
        this.status = true;
        this.ownerName = reqDTO.getOwnerName();
        this.ownerTel = reqDTO.getOwnerTel();
        this.ownerEmail = reqDTO.getOwnerEmail();
        this.bizNum = reqDTO.getBizNum();
        this.name = reqDTO.getName();
        this.tel = reqDTO.getTel();
        this.address = reqDTO.getAddress();
        this.latitude = reqDTO.getLatitude();
        this.longitude = reqDTO.getLongitude();
    }

    public void update(StoreRequest.UpdateDTO reqDTO) {
        setPassword(reqDTO.getPassword());
        setOwnerName(reqDTO.getOwnerName());
        setOwnerTel(reqDTO.getOwnerTel());
        setOwnerEmail(reqDTO.getOwnerEmail());
        setBizNum(reqDTO.getBizNum());
        setName(reqDTO.getName());
        setTel(reqDTO.getTel());
        setIntro(reqDTO.getIntro());
        setOpeningTime(reqDTO.getOpeningTime());
        setClosingTime(reqDTO.getClosingTime());
        setClosedDay(reqDTO.getClosedDay());
        setAddress(reqDTO.getAddress());
        setLatitude(reqDTO.getLatitude());
        setLongitude(reqDTO.getLongitude());
    }

    private void setImgFilename(String encodedFile) {
        FileUtil.deleteFile(this.imgFilename);
        String imgFilename = FileUtil.uploadBase64(encodedFile, this.name);
        if (imgFilename.equals("default")) { // TODO: 삭제 로직 분리하기, 사진 유지 추가
            this.imgFilename = DefaultFile.STORE.getPath();
        } else {
            this.imgFilename = imgFilename;
        }
    }
}
