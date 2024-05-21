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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver.MyRestDoc;
import shop.project.pathorderserver._core.utils.JwtUtil;
import shop.project.pathorderserver.user.SessionUser;
import shop.project.pathorderserver.user.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LikeControllerTest extends MyRestDoc {
    private ObjectMapper om = new ObjectMapper();

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
        ResultActions actions = mockMvc.perform(
                post("/api/users/" + sessionUser.getId() + "/likes")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.userId").value(1));
        actions.andExpect(jsonPath("$.body.storeId").value(5));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 좋아요 추가 실패(존재하지 않는 스토어)
    @Test
    public void add_like_fail_test() throws Exception {
        //given
        LikeRequest.AddLikeDTO reqDTO = new LikeRequest.AddLikeDTO();
        reqDTO.setStoreId(999);
        String reqBody = om.writeValueAsString(reqDTO);
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        // when
        ResultActions actions = mockMvc.perform(
                post("/api/users/" + sessionUser.getId() + "/likes")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        // then
        actions.andExpect(status().isNotFound());
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.msg").value("해당하는 매장을 찾을 수 없습니다."));
        actions.andExpect(jsonPath("$.body").isEmpty());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
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
        ResultActions actions = mockMvc.perform(
                delete("/api/users/" + sessionUser.getId() + "/likes")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.userId").value(1));
        actions.andExpect(jsonPath("$.body.storeId").value(1));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 좋아요 삭제 실패
    @Test
    public void delete_like_fail_test() throws Exception {
        //given
        LikeRequest.RemoveLikeDTO reqDTO = new LikeRequest.RemoveLikeDTO();
        reqDTO.setStoreId(999);
        String reqBody = om.writeValueAsString(reqDTO);
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        // when
        ResultActions actions = mockMvc.perform(
                delete("/api/users/" + sessionUser.getId() + "/likes")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        // then
        actions.andExpect(status().isNotFound());
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.msg").value("해당 좋아요가 존재하지 않습니다."));
        actions.andExpect(jsonPath("$.body").isEmpty());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 특정 사용자의 좋아요 목록 조회 성공
    @Test
    public void get_users_like_success_test() throws Exception {
        //given
        int userId = 1;
        // when
        ResultActions actions = mockMvc.perform(
                get("/api/users/" + userId + "/likes")
                        .header("Authorization", "Bearer " + jwt)
        );
        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].id").value(1));
        actions.andExpect(jsonPath("$.body[0].storeId").value(1));
        actions.andExpect(jsonPath("$.body[0].storeImgFilename").value("default/cafe1.png"));
        actions.andExpect(jsonPath("$.body[0].storeName").value("연의양과"));
        actions.andExpect(jsonPath("$.body[0].distance").value(356));
        actions.andExpect(jsonPath("$.body[0].likeCount").value(3));
        actions.andExpect(jsonPath("$.body[0].reviewCount").value(2));
        actions.andExpect(jsonPath("$.body[0].latitude").value(35.1587487392983));
        actions.andExpect(jsonPath("$.body[0].longitude").value(129.064002552455));
        actions.andExpect(jsonPath("$.body[0].like").value(true));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 특정 사용자의 좋아요 목록 조회 실패
    @Test
    public void get_users_like_fail_test() throws Exception {
        //given
        int userId = 999;
        // when
        ResultActions actions = mockMvc.perform(
                get("/api/users/" + userId + "/likes")
                        .header("Authorization", "Bearer " + jwt)
        );
        // then
        actions.andExpect(status().isForbidden());
        actions.andExpect(jsonPath("$.status").value(403));
        actions.andExpect(jsonPath("$.msg").value("해당 좋아요 리스트를 열람하실 수 없습니다."));
        actions.andExpect(jsonPath("$.body").isEmpty());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
