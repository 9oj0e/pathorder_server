package shop.project.pathorderserver.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.utils.JwtUtil;
import shop.project.pathorderserver.user.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ReviewControllerTest {

    private ObjectMapper om = new ObjectMapper();
    private static String jwt;

    @BeforeAll
    public static void setUp() {
        jwt = JwtUtil.create(
                User.builder()
                        .id(1)
                        .username("user1")
                        .nickname("성재")
                        .build()
        );
    }

    @Autowired
    private MockMvc mvc;

    // 리뷰 등록 성공
    @Test
    public void addReview_success_test() throws Exception {
        //given
        int storeId = 1;
        ReviewRequest.AddDTO reqDTO = new ReviewRequest.AddDTO();
        reqDTO.setContent("맛있었어요");
        reqDTO.setEncodedData(null);

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        //when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders.post("/api/stores/" + storeId + "/reviews")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.content").value("맛있었어요"));
        actions.andExpect(jsonPath("$.body.encodedData").isEmpty());

    }

    // 리뷰 등록 실패 - 공백
    @Test
    public void addReview_content_blank_fail_test() throws Exception {
        //given
        int storeId = 1;
        ReviewRequest.AddDTO reqDTO = new ReviewRequest.AddDTO();

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        //when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders.post("/api/stores/" + storeId + "/reviews")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.msg").value("내용을 입력해주세요."));
        actions.andExpect(jsonPath("$.body").isEmpty());
    }

    // 리뷰 등록 실패 - 5자 미만
    @Test
    public void addReview_content_size_fail_test() throws Exception {
        //given
        int storeId = 1;
        ReviewRequest.AddDTO reqDTO = new ReviewRequest.AddDTO();
        reqDTO.setContent("1234");

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        //when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders.post("/api/stores/" + storeId + "/reviews")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.msg").value("내용은 5자 이상 입력해주세요."));
        actions.andExpect(jsonPath("$.body").isEmpty());

    }

    // 내 리뷰 보기
    @Test
    public void myReviewList_test() throws Exception {
        //given
        int userId = 1;
        //when
        ResultActions actions = mvc.perform(
                get("/api/users/" + userId + "/reviews")
                        .header("Authorization", "Bearer " + jwt)
        );
        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.reviewList.[0].userId").value(1));
        actions.andExpect(jsonPath("$.body.reviewList.[0].nickname").value("성재"));
        actions.andExpect(jsonPath("$.body.reviewList.[0].usersImgFilePath").value("/upload/default/avatar.png"));
        actions.andExpect(jsonPath("$.body.reviewList.[0].reviewId").value(2));
        actions.andExpect(jsonPath("$.body.reviewList.[0].content").value("맛있어요"));
        actions.andExpect(jsonPath("$.body.reviewList.[0].imgFilePath").isEmpty());
        actions.andExpect(jsonPath("$.body.reviewList.[0].createdAt").value("24/05/20"));
    }

    // 매장 리뷰 보기
    @Test
    public void storeReviewList_test() throws Exception {
        //given
        int storeId = 1;
        //when
        ResultActions actions = mvc.perform(
                get("/api/stores/" + storeId + "/reviews")
                        .header("Authorization", "Bearer " + jwt)
        );
        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.reviewList.[0].userId").value(2));
        actions.andExpect(jsonPath("$.body.reviewList.[0].nickname").value("정현"));
        actions.andExpect(jsonPath("$.body.reviewList.[0].usersImgFilePath").value("/upload/default/avatar.png"));
        actions.andExpect(jsonPath("$.body.reviewList.[0].reviewId").value(3));
        actions.andExpect(jsonPath("$.body.reviewList.[0].content").value("맛있어요"));
        actions.andExpect(jsonPath("$.body.reviewList.[0].reviewsImgFilePath").isEmpty());
        actions.andExpect(jsonPath("$.body.reviewList.[0].createdAt").value("24/05/20"));
    }
}
