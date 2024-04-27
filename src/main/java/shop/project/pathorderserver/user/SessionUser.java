package shop.project.pathorderserver.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class SessionUser {
    // 앞으로 외부에 노출되는 데이터는 SessionUser, DB에 전달될 데이터는 User
    private Integer id;
    // 회원 정보
    private String username;
    private Timestamp createdAt;

    @Builder
    public SessionUser(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}

