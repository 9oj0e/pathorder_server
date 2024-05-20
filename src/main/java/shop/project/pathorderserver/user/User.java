package shop.project.pathorderserver.user;

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
@Table(name = "user_tb", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "tel"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "아이디는 필수 항목입니다.")
    @Size(min = 3, max = 12, message = "아이디는 3-12자 사이여야 합니다.")
    @Pattern(regexp = "^[^\\s]+$", message = "아이디는 공백을 포함할 수 없습니다.")
    private String username; // 로그인 아이디

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4-20자 사이여야 합니다.")
    @Pattern(regexp = "^[^\\s]+$", message = "비밀번호는 공백을 포함할 수 없습니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 항목입니다.")
    @Size(min = 2, max = 4, message = "닉네임은 2-4자 사이여야 합니다.")
    @Pattern(regexp = "^[^\\s]+$", message = "닉네임은 공백을 포함할 수 없습니다.")
    private String nickname; // 회원 별명

    @ColumnDefault("true")
    private boolean status; // 계정 상태 (false : 비활성, true : 활성)

    @NotBlank(message = "이름은 필수 항목입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 항목입니다.")
    @Size(max = 11, message = "전화번호는 최대 11자까지 가능합니다.")
    @Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자만 포함할 수 있습니다.")
    private String tel; // 전화번호

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
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
        if (imgFilename.equals("default")) {
            this.imgFilename = DefaultFile.AVATAR.getPath();
        } else {
            this.imgFilename = imgFilename;
        }
    }
}