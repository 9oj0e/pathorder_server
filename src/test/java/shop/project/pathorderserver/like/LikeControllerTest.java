package shop.project.pathorderserver.like;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import shop.project.pathorderserver._core.utils.JwtUtil;
import shop.project.pathorderserver.user.SessionUser;
import shop.project.pathorderserver.user.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LikeControllerTest {
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    private static String jwt;
    private static MockHttpSession session;

    @BeforeAll
    public static void setUp() {
        jwt = JwtUtil.create(
                User.builder()
                        .id(1)
                        .username("user1")
                        .password("1234")
                        .nickname("성재")
                        .build());

        session = new MockHttpSession();
        SessionUser sessionUser = new SessionUser(1, "user1", "성재");
        session.setAttribute("sessionUser", sessionUser);
    }

    // 좋아요 추가 성공
    @Test
    public void add_like_success_test() throws Exception {
        //given
        LikeRequest.AddLikeDTO reqDTO = new LikeRequest.AddLikeDTO();
        reqDTO.setStoreId(5);

        String reqBody = om.writeValueAsString(reqDTO);

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        // when
        ResultActions actions = mvc.perform(
                post("/api/users/" + sessionUser.getId() + "/likes")
                        .header("Authorization", "Bearer " + jwt)
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
        actions.andExpect(jsonPath("$.body.userId").value(1));
        actions.andExpect(jsonPath("$.body.storeId").value(5));
    }

    // 좋아요 추가 실패(존재하지 않는 스토어)
    @Test
    public void add_like_with_not_found_store_fail_test() throws Exception {
        //given
        LikeRequest.AddLikeDTO reqDTO = new LikeRequest.AddLikeDTO();
        reqDTO.setStoreId(999);

        String reqBody = om.writeValueAsString(reqDTO);

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        // when
        ResultActions actions = mvc.perform(
                post("/api/users/" + sessionUser.getId() + "/likes")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(status().isNotFound());
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.msg").value("해당하는 매장을 찾을 수 없습니다."));
        actions.andExpect(jsonPath("$.body").isEmpty());
    }

    // 좋아요 삭제 성공
    @Test
    public void delete_like_success_test() throws Exception {
        //given
        LikeRequest.RemoveLikeDTO reqDTO = new LikeRequest.RemoveLikeDTO();
        reqDTO.setStoreId(1);

        String reqBody = om.writeValueAsString(reqDTO);

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        // when
        ResultActions actions = mvc.perform(
                delete("/api/users/" + sessionUser.getId() + "/likes")
                        .header("Authorization", "Bearer " + jwt)
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
        actions.andExpect(jsonPath("$.body.userId").value(1));
        actions.andExpect(jsonPath("$.body.storeId").value(1));
    }
    // 좋아요 삭제 실패
    @Test
    public void delete_like_with_not_found_store_fail_test() throws Exception {
        //given
        LikeRequest.RemoveLikeDTO reqDTO = new LikeRequest.RemoveLikeDTO();
        reqDTO.setStoreId(999);

        String reqBody = om.writeValueAsString(reqDTO);

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        // when
        ResultActions actions = mvc.perform(
                delete("/api/users/" + sessionUser.getId() + "/likes")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
//        actions.andExpect(status().isOk());
//        actions.andExpect(jsonPath("$.status").value(200));
//        actions.andExpect(jsonPath("$.msg").value("성공"));
//        actions.andExpect(jsonPath("$.body.userId").value(1));
//        actions.andExpect(jsonPath("$.body.storeId").value(1));
    }

    // 특정 사용자의 좋아요 목록 조회 성공

    // 특정 사용자의 좋아요 목록 조회 실패
}
