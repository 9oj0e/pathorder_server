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
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver.MyRestDoc;
import shop.project.pathorderserver._core.utils.JwtUtil;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest extends MyRestDoc {
    private ObjectMapper om = new ObjectMapper();

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
        ResultActions actions = mockMvc.perform(
                post("/join")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
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
    public void join_fail_test() throws Exception {
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
        ResultActions actions = mockMvc.perform(
                post("/join")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
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
        ResultActions actions = mockMvc.perform(
                post("/login")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
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
    public void login_fail_test() throws Exception {
        //given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("user99");
        reqDTO.setPassword("1234");
        String reqBody = om.writeValueAsString(reqDTO);
        // when
        ResultActions actions = mockMvc.perform(
                post("/login")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        // then
        actions.andExpect(status().isUnauthorized());
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.msg").value("아이디 또는 비밀번호가 틀렸습니다."));
        actions.andExpect(jsonPath("$.body").isEmpty());
    }

    // 회원정보조회 성공
    @Test
    public void get_user_info_success_test() throws Exception {
        //given
        int userId = 1;
        // when
        ResultActions actions = mockMvc.perform(
                get("/api/users/" + userId)
                        .header("Authorization", "Bearer " + jwt)
        );
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
    public void get_user_info_fail_test() throws Exception {
        //given
        int userId = 999;
        // when
        ResultActions actions = mockMvc.perform(
                get("/api/users/" + userId)
                        .header("Authorization", "Bearer " + jwt)
        );
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
        reqDTO.setEmail("ryu@nate.com");
        reqDTO.setTel("01098765432");
        String reqBody = om.writeValueAsString(reqDTO);
        // when
        ResultActions actions = mockMvc.perform(
                put("/api/users/" + userId)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );
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

    // 회원정보 수정 실패(유효성 검사 실패)
    @Test
    public void update_user_info_fail_test() throws Exception {
        //given
        int userId = 1;
        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setNickname("재성");
        reqDTO.setEmail("invalid-email");
        reqDTO.setTel("01098765432");

        String reqBody = om.writeValueAsString(reqDTO);
        // when
        ResultActions actions = mockMvc.perform(
                put("/api/users/" + userId)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );
        // then
        actions.andExpect(status().isBadRequest());
        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.msg").value("유효한 이메일 형식이어야 합니다."));
    }

//    // TODO: 사진 업로드?
//    // 사진 등록 성공
//    @Test
//    public void upload_img_success_test() throws Exception {
//        //given
//        int userId = 1;
//        UserRequest.ImgDTO reqDTO = new UserRequest.ImgDTO();
//        reqDTO.setUsername("user1");
//        reqDTO.setEncodedImg("파일이름");
//
//        String reqBody = om.writeValueAsString(reqDTO);
//
//        // when
//        ResultActions actions = mvc.perform(
//                post("/api/users/" + userId)
//                        .header("Authorization", "Bearer " + jwt)
//                        .content(reqBody)
//                        .contentType(MediaType.APPLICATION_JSON)
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
////        actions.andExpect(jsonPath("$.body.imgFilename").exists());
//    }

//    // 사진 등록 실패(사진을 선택하지 않음)
//    @Test
//    public void upload_img_fail_test() throws Exception {
//        //given
//        int userId = 1;
//        UserRequest.ImgDTO reqDTO = new UserRequest.ImgDTO();
//        reqDTO.setUsername("user1");
//        reqDTO.setEncodedImg(null);
//
//        String reqBody = om.writeValueAsString(reqDTO);
//
//        // when
//        ResultActions actions = mvc.perform(
//                post("/api/users/" + userId)
//                        .header("Authorization", "Bearer " + jwt)
//                        .content(reqBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        // eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody = " + respBody);
//
//        // then
//        actions.andExpect(status().isBadRequest());
//        actions.andExpect(jsonPath("$.status").value(400));
//    }

    // 주문하기 성공
    @Test
    public void order_success_test() throws Exception {
        //given (주문, 메뉴, 메뉴옵션)
        UserRequest.OrderDTO reqDTO = new UserRequest.OrderDTO();
        reqDTO.setStoreId(1);
        reqDTO.setStoreName("아리수");
        reqDTO.setCustomerId(3);
        reqDTO.setCustomerNickname("현정");
        reqDTO.setRequest("30분 뒤에 찾으러 갈게요.");
        reqDTO.setOrderMenuList(new ArrayList<>());
        UserRequest.OrderDTO.OrderMenuDTO orderMenu = new UserRequest.OrderDTO.OrderMenuDTO();
        orderMenu.setName("아메리카노");
        orderMenu.setPrice(3000);
        orderMenu.setQty(2);
        UserRequest.OrderDTO.OrderMenuOptionDTO orderMenuOption = new UserRequest.OrderDTO.OrderMenuOptionDTO();
        orderMenuOption.setName("아이스");
        orderMenuOption.setPrice(0);
        orderMenu.getOrderMenuOptionList().add(orderMenuOption);
        reqDTO.getOrderMenuList().add(orderMenu);

        String reqBody = om.writeValueAsString(reqDTO);
        // when
        ResultActions actions = mockMvc.perform(
                post("/api/users/" + 1 + "/orders")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.customerId").value(3));
        actions.andExpect(jsonPath("$.body.customerNickname").value("현정"));
        actions.andExpect(jsonPath("$.body.storeId").value(1));
        actions.andExpect(jsonPath("$.body.storeName").value("아리수"));
        actions.andExpect(jsonPath("$.body.id").value(22)); //22 ~ 23
        actions.andExpect(jsonPath("$.body.request").value("30분 뒤에 찾으러 갈게요."));
        actions.andExpect(jsonPath("$.body.status").value("PENDING"));
        actions.andExpect(jsonPath("$.body.orderMenuList[0].id").value(80)); //78 ~ 80
        actions.andExpect(jsonPath("$.body.orderMenuList[0].name").value("아메리카노"));
        actions.andExpect(jsonPath("$.body.orderMenuList[0].price").value("3,000"));
        actions.andExpect(jsonPath("$.body.orderMenuList[0].qty").value(2));
        actions.andExpect(jsonPath("$.body.orderMenuList[0].totalPrice").value(6000));
        actions.andExpect(jsonPath("$.body.orderMenuList[0].orderMenuOptionList[0].name").value("아이스"));
        actions.andExpect(jsonPath("$.body.orderMenuList[0].orderMenuOptionList[0].price").value("0"));
    }

    // 주문하기 실패(이상한 메뉴)
    @Test
    public void order_fail_test() throws Exception {
        // given
        UserRequest.OrderDTO reqDTO = new UserRequest.OrderDTO();
        reqDTO.setStoreId(1);
        reqDTO.setStoreName("스타벅스");
        reqDTO.setCustomerId(1);
        reqDTO.setCustomerNickname("성재");
        reqDTO.setRequest("날아오세요.");
        reqDTO.setOrderMenuList(new ArrayList<>());

        // 유효하지 않은 주문 메뉴 추가
        UserRequest.OrderDTO.OrderMenuDTO orderMenu = new UserRequest.OrderDTO.OrderMenuDTO();
        orderMenu.setName("아메리카노");
        orderMenu.setPrice(-3000); // Invalid price
        orderMenu.setQty(1); // Valid quantity

        reqDTO.getOrderMenuList().add(orderMenu);
        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mockMvc.perform(
                post("/api/users/" + 1 + "/orders")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        // then
        actions.andExpect(status().isBadRequest());
        actions.andExpect(jsonPath("$.msg").value("가격은 음수일 수 없습니다."));
    }

    // 회원 주문내역 목록보기 성공
    @Test
    public void order_list_test() throws Exception {
        //given
        int userId = 1;
        // when
        ResultActions actions = mockMvc.perform(
                get("/api/users/" + userId + "/orders")
                        .header("Authorization", "Bearer " + jwt)
        );
        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.orderList").isArray());
    }

    // 회원 주문내역 상세보기 성공
    @Test
    public void order_detail_success_test() throws Exception {
        //given
        int userId = 1;
        int orderId = 1;
        // when
        ResultActions actions = mockMvc.perform(
                get("/api/users/" + userId + "/orders/" + orderId)
                        .header("Authorization", "Bearer " + jwt)
        );
        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
    }

    // 회원 주문내역 상세보기 실패(존재하지 않는 주문)
    @Test
    public void order_detail_fail_test() throws Exception {
        //given
        int userId = 1;
        int orderId = 999; // 존재하지 않는 주문 ID
        // when
        ResultActions actions = mockMvc.perform(
                get("/api/users/" + userId + "/orders/" + orderId)
                        .header("Authorization", "Bearer " + jwt)
        );
        // then
        actions.andExpect(status().isNotFound());
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.msg").value("찾을 수 없는 주문입니다."));
    }
}