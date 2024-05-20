package shop.project.pathorderserver.user;

import jakarta.persistence.*;
import lombok.Builder;
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
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 회원 정보
    private String username; // 로그인 아이디
    private String password;
    private String nickname; // 회원 별명
    @ColumnDefault("true")
    private boolean status; // 계정 상태 (false : 비활성, true : 활성)
    // 개인 정보
    private String name;
    private String tel; // 전화번호
    private String email;
    @ColumnDefault("'default/avatar.png'")
    private String imgFilename;

    @CreationTimestamp
    private Timestamp registeredAt; // 가입일

    @Builder
    public User(int id, String username, String password, String nickname, boolean status, String name, String tel, String email, String imgFilename, Timestamp registeredAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.status = status;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.imgFilename = imgFilename;
        this.registeredAt = registeredAt;
    }

    public User(UserRequest.JoinDTO reqDTO) {
        this.username = reqDTO.getUsername();
        this.password = reqDTO.getPassword();
        this.nickname = reqDTO.getNickname();
        this.name = reqDTO.getName();
        this.tel = reqDTO.getTel();
        this.email = reqDTO.getEmail();
    }

    public void update(UserRequest.UpdateDTO reqDTO) {
        if (!reqDTO.getNickname().isBlank()) {
            this.nickname = reqDTO.getNickname();
        }
        if (!reqDTO.getTel().isBlank()) {
            this.tel = reqDTO.getTel();
        }
        if (!reqDTO.getEmail().isBlank()) {
            this.email = reqDTO.getEmail();
        }
    }

    public void setImgFilename(String encodedFile) {
        FileUtil.deleteFile(this.imgFilename);
        String imgFilename = FileUtil.uploadBase64Jpg(encodedFile, this.name);
        // String imgFilename = FileUtil.uploadBase64(encodedFile, this.name); // 이외 확장자 처리 메서드
        if (imgFilename.equals("default")) { // TODO: 삭제 로직 분리하기, 사진 유지 추가
            this.imgFilename = DefaultFile.AVATAR.getPath();
        } else {
            this.imgFilename = imgFilename;
        }
    }
}
