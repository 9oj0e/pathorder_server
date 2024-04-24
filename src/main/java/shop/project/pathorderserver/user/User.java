package shop.project.pathorderserver.user;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // 회원 정보
    private String username;
    private String password;
    private String nickname; // 회원 별명
    private boolean status; // 계정 상태 (0 : 비활성, 1 : 활성)
    private Timestamp registered_at; // 가입일
    // 개인 정보
    private String name;
    private String tel; // 전화번호
    private String email;
    private String imgSrc;
}
