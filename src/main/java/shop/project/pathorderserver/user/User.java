package shop.project.pathorderserver.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
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

    public User(UserRequest.JoinDTO reqDTO) {
        this.username = reqDTO.getUsername();
        this.password = reqDTO.getPassword();
        this.nickname = reqDTO.getNickname();
        this.name = reqDTO.getName();
        this.tel = reqDTO.getTel();
        this.email = reqDTO.getEmail();
    }

    public void update(UserRequest.UpdateDTO reqDTO) {
        /*
        this.nickname = reqDTO.getNickname();
        this.tel = reqDTO.getTel();
        this.email = reqDTO.getEmail();
        */
        if (!reqDTO.getNickname().isBlank()) {
            setNickname(reqDTO.getNickname());
        }
        if (!reqDTO.getTel().isBlank()) {
            setTel(reqDTO.getTel());
        }
        if (!reqDTO.getEmail().isBlank()) {
            setEmail(reqDTO.getEmail());
        }
    }
}
