package shop.project.pathorderserver.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.project.pathorderserver._core.utils.JwtUtil;
import shop.project.pathorderserver.order.Order;
import shop.project.pathorderserver.order.OrderMenu;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test // 회원가입
    void createUser_test() {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setUsername("username1234");
        reqDTO.setPassword("1234");
        reqDTO.setNickname("NAME");
        reqDTO.setName("홍길동");
        reqDTO.setTel("01012345678");
        reqDTO.setEmail("ssar@nate.com");
        // when
        UserResponse.JoinDTO respDTO = userService.createUser(reqDTO);
        // then
        Assertions.assertThat(respDTO.getUsername()).isEqualTo("username1234");
        Assertions.assertThat(respDTO.getNickname()).isEqualTo("NAME");
    }

    @Test // 로그인
    void getUser1_test() {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("user2");
        reqDTO.setPassword("1234");
        // when
        UserResponse.LoginDTO respDTO = userService.getUser(reqDTO);
        // then
        SessionUser sessionUser = JwtUtil.verify(respDTO.getJwt());
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

    @Test // 주문하기
    void createOrder_test() {
        // given
        // when
        // then
    }
    @Test // 주문내역 목록보기
    void getOrderList_test() {
        // given
        // when
        // then
    }

    @Test // 주문내역 상세보기
    void getOrderDetail_test() {
        // given
        int orderId = 1;
        // Order order = new Order();
        // List<OrderMenu> orderMenus = new ArrayList<>();
        // UserResponse.OrderDetailDTO respDTO = new UserResponse.OrderDetailDTO(order, orderMenus);
        // when
        UserResponse.OrderDetailDTO respDTO = userService.getOrderDetail(orderId);
        // then
        // System.out.println("test: " + respDTO.getTotalPrice());
        Assertions.assertThat(respDTO.getTotalPrice()).isEqualTo("23,300");
    }
}
