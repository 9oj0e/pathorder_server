package shop.project.pathorderserver.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import shop.project.pathorderserver._core.utils.JwtUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    private static String jwt;

    @BeforeAll
    public static void setUp() {
        jwt = JwtUtil.create(
                User.builder()
                        .id(1)
                        .username("user1")
                        .password("1234")
                        .nickname("성재")
                        .build());
    }

    // 회원가입 성공
    @Test
    public void join_success_test() throws Exception {
        //given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setUsername("captainkong");
        reqDTO.setPassword("1234");
        reqDTO.setNickname("지영잉");
        reqDTO.setName("공지영");
        reqDTO.setTel("01085269874");
        reqDTO.setEmail("captain_kong@nate.com");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/join")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.username").value("captainkong"));
        actions.andExpect(jsonPath("$.body.password").value("1234"));
        actions.andExpect(jsonPath("$.body.nickname").value("지영잉"));
        actions.andExpect(jsonPath("$.body.name").value("공지영"));
        actions.andExpect(jsonPath("$.body.tel").value("01085269874"));
        actions.andExpect(jsonPath("$.body.email").value("captain_kong@nate.com"));
    }

    // 회원가입 실패(중복되는 유저네임 존재)
    @Test
    public void join_username_same_fail_test() throws Exception {
        //given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setUsername("user1");
        reqDTO.setPassword("1234");
        reqDTO.setNickname("재성");
        reqDTO.setName("류재성");
        reqDTO.setTel("01085269874");
        reqDTO.setEmail("ryu@nate.com");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/join")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(status().isBadRequest());
        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.msg").value("중복된 유저입니다."));
        actions.andExpect(jsonPath("$.body").isEmpty());
    }

    // TODO: 유효성 검사 체크해서 다시
    // 로그인 성공
    @Test
    public void login_success_test() throws Exception {
        //given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("user1");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/login")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.id").value(1));
        actions.andExpect(jsonPath("$.body.nickname").value("성재"));
        actions.andExpect(jsonPath("$.body.name").value("김성재"));
        actions.andExpect(jsonPath("$.body.tel").value("01012345555"));
        actions.andExpect(jsonPath("$.body.email").value("user1@gmail.com"));
        actions.andExpect(jsonPath("$.body.imgFilename").value("default/avatar.png"));
    }

    // 로그인 실패(유저네임 불일치)
    @Test
    public void login_username_not_found_fail_test() throws Exception {
        //given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("user99");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/login")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(status().isUnauthorized());
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.msg").value("아이디 또는 비밀번호가 틀렸습니다."));
        actions.andExpect(jsonPath("$.body").isEmpty());
    }

    // 로그아읏 성공
    @Test
    public void logout_success_test() throws Exception {
        //given
        String jwt = JwtUtil.create(
                User.builder()
                        .id(1)
                        .username("user1")
                        .password("1234")
                        .nickname("성재")
                        .build());

        // when
        ResultActions actions = mvc.perform(
                get("/logout")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body").value("로그아웃 완료"));
    }

    // 회원정보조회 성공
    @Test
    public void get_user_info_success_test() throws Exception {
        //given
        int userId = 1;

        // when
        ResultActions actions = mvc.perform(
                get("/api/users/" + userId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.id").value(1));
        actions.andExpect(jsonPath("$.body.nickname").value("성재"));
        actions.andExpect(jsonPath("$.body.email").value("user1@gmail.com"));
        actions.andExpect(jsonPath("$.body.tel").value("010-1234-5555"));
        actions.andExpect(jsonPath("$.body.imgFilename").value("default/avatar.png"));
    }

    // 회원정보조회 실패(존재하지 않는 유저 아이디)
    @Test
    public void get_user_info_not_found_fail_test() throws Exception {
        //given
        int userId = 999;

        // when
        ResultActions actions = mvc.perform(
                get("/api/users/" + userId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(status().isNotFound());
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.msg").value("찾을 수 없는 유저입니다."));
        actions.andExpect(jsonPath("$.body").isEmpty());
    }

    // TODO: 유효성 검사 체크해서 다시
    // 회원정보 수정 성공
    @Test
    public void update_user_info_success_test() throws Exception {
        //given
        int userId = 1;
        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setNickname("재성");
        reqDTO.setTel("010-9876-5432");
        reqDTO.setEmail("ryu@nate.com");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                put("/api/users/" + userId)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.id").value(1));
        actions.andExpect(jsonPath("$.body.username").value("user1"));
        actions.andExpect(jsonPath("$.body.nickname").value("재성"));
        actions.andExpect(jsonPath("$.body.latitude").value(35.15743361723729));
        actions.andExpect(jsonPath("$.body.longitude").value(129.0604337191542));
    }

    // TODO: 유효성 검사 체크해서 다시: 이건 완성조차 아님
//    // 회원정보 수정 실패
//    @Test
//    public void update_user_info_validation_fail_test() throws Exception {
//        //given
//        int userId = 1;
//        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
//        reqDTO.setNickname("재성");
//        reqDTO.setTel("010-9876-5432");
//        reqDTO.setEmail("ryu");
//
//        String reqBody = om.writeValueAsString(reqDTO);
//
//        // when
//        ResultActions actions = mvc.perform(
//                put("/api/users/" + userId)
//                        .header("Authorization", "Bearer " + jwt)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(reqBody)
//        );
//
//        // eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody = " + respBody);
//
//        // then
////        actions.andExpect(status().isOk());
////        actions.andExpect(jsonPath("$.status").value(200));
////        actions.andExpect(jsonPath("$.msg").value("성공"));
////        actions.andExpect(jsonPath("$.body.id").value(1));
////        actions.andExpect(jsonPath("$.body.username").value("user1"));
////        actions.andExpect(jsonPath("$.body.nickname").value("재성"));
////        actions.andExpect(jsonPath("$.body.latitude").value(35.15743361723729));
////        actions.andExpect(jsonPath("$.body.longitude").value(129.0604337191542));
//    }

    // 사진 등록 성공
    // 사진 등록 실패

    // 주문하기 성공
    // 주문하기 실패

    // 회원 주문내역 목록보기 성공
    // 회원 주문내역 목록보기 실패

    // 회원 주문내역 상세보기 성공
    // 회원 주문내역 상세보기 실패
}
