package shop.project.pathorderserver.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SessionUser {
    // 앞으로 외부에 노출되는 데이터는 SessionUser, DB에 전달될 데이터는 User
    private int id;
    // 회원 정보
    private String username;
    private String nickname;
    // 위치 정보
    private double latitude;
    private double longitude;

    @Builder
    public SessionUser(int id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.latitude = 35.15743361723729;
        this.longitude = 129.0604337191542;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.latitude = 35.15743361723729;
        this.longitude = 129.0604337191542;
    }
}