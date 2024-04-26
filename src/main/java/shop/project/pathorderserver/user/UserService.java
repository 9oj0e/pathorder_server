package shop.project.pathorderserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional // 회원 가입
    public UserResponse.JoinDTO createUser(UserRequest.JoinDTO reqDTO) {

        return null;
    }

    // 로그인
    public UserResponse.LoginDTO getUser(UserRequest.LoginDTO reqDTO) {

        return null;
    }

    // 회원정보 보기
    public UserResponse.UserDTO getUser(int userId) {

        return null;
    }

    @Transactional // 회원정보 수정
    public UserResponse.UpdateDTO setUser(UserRequest.UpdateDTO reqDTO) {

        return null;
    }
}
