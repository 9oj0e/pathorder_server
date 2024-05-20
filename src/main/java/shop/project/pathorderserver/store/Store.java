package shop.project.pathorderserver.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "로그인 아이디는 필수 입력값입니다.")
    @Size(min = 3, max = 12, message = "로그인 아이디는 3자 이상 12자 이하로 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "로그인 아이디는 영어와 숫자만 사용할 수 있습니다.")
    @Column(unique = true)
    private String username; // 로그인 아이디

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하로 입력해주세요.")
    @Pattern(regexp = "^\\S+$", message = "비밀번호는 공백을 포함할 수 없습니다.")
    private String password;

    @ColumnDefault("true")
    private boolean status; // 계정 상태(0 : 비활성, 1 : 활성)

    @NotBlank(message = "사업자 이름은 필수 입력값입니다.")
    private String ownerName; // 사업자 이름

    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    @Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "전화번호에는 숫자만 입력해주세요.")
    @Column(unique = true)
    private String ownerTel;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String ownerEmail;

    @NotBlank(message = "사업자 등록 번호는 필수 입력값입니다.")
    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "사업자 등록 번호 형식이 올바르지 않습니다. (예: 123-12-12345)")
    @Column(unique = true)
    private String bizNum; // 사업자 등록 번호

    @NotBlank(message = "매장 이름은 필수 입력값입니다.")
    private String name;

    @Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "매장 전화번호는 숫자만 입력해주세요.")
    @Column(unique = true)
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
