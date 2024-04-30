package shop.project.pathorderserver.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.project.pathorderserver._core.utils.JwtUtil;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test // 회원가입
    void createUser_test() {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setUsername("ssar");
        reqDTO.setPassword("1234");
        reqDTO.setNickname("ssarman");
        reqDTO.setEmail("ssar@nate.com");
        // when
        UserResponse.JoinDTO respDTO = userService.createUser(reqDTO);
        // then
        Assertions.assertThat(respDTO.getUsername()).isEqualTo("ssar");
        Assertions.assertThat(respDTO.getNickname()).isEqualTo("ssarman");
    }

    @Test // 로그인
    void getUser1_test() {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("user2");
        reqDTO.setPassword("1234");
        // when
        String jwt = userService.getUser(reqDTO);
        // then
        SessionUser sessionUser = JwtUtil.verify(jwt);
        Assertions.assertThat(sessionUser.getUsername()).isEqualTo("user2");
    }

    @Test // 회원정보 조회
    void getUser2_test() {
        // given
        int sessionUserId = 2;
        // when
        UserResponse.UserDTO respDTO = userService.getUser(sessionUserId);
        // then
        Assertions.assertThat(respDTO.getNickname()).isEqualTo("정현");
    }

    @Test // 회원정보 수정
    void setUser_test() {
        // given
        int sessionUserId = 2;
        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setNickname("test");
        reqDTO.setEmail("ssar@nate.com");
        reqDTO.setTel("01020304");
        // when
        // UserResponse.UpdateDTO respDTO = userService.setUser(reqDTO, sessionUserId);
        // then
        // Assertions.assertThat(respDTO.getNickname()).isEqualTo("test");
        // Assertions.assertThat(respDTO.getEmail()).isEqualTo("ssar@nate.com");
        // Assertions.assertThat(respDTO.getTel()).isEqualTo("01020304");
    }
}
