package shop.project.pathorderserver.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import shop.project.pathorderserver.MyRestDoc;
import shop.project.pathorderserver._core.utils.JwtUtil;
import shop.project.pathorderserver.user.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class StoreControllerTest extends MyRestDoc {

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

    // 매장 목록보기
    @Test
    public void storeList_test() throws Exception {
        //given
        //when
        ResultActions actions = mockMvc.perform(
                get("/api/stores")
                        .header("Authorization", "Bearer " + jwt)
        );
        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body[0].id").value(4));
        actions.andExpect(jsonPath("$.body[0].imgFilename").value("default/cafe4.png"));
        actions.andExpect(jsonPath("$.body[0].name").value("블랙업커피 서면본점"));
        actions.andExpect(jsonPath("$.body[0].distance").value(159));
        actions.andExpect(jsonPath("$.body[0].likeCount").value(3));
        actions.andExpect(jsonPath("$.body[0].latitude").value(35.1560557306354));
        actions.andExpect(jsonPath("$.body[0].longitude").value(129.059978704814));
        actions.andExpect(jsonPath("$.body[0].reviewCount").value(0));
        actions.andExpect(jsonPath("$.body[0].liked").value(false));
        // actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 매장 상세보기
    @Test
    public void storeInfo_test() throws Exception {
        //given
        int storeId = 1;
        //when
        ResultActions actions = mockMvc.perform(
                get("/api/stores/" + storeId)
                        .header("Authorization", "Bearer " + jwt)
        );
        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.id").value(1));
        actions.andExpect(jsonPath("$.body.imgFilename").value("default/cafe1.png"));
        actions.andExpect(jsonPath("$.body.intro").value("까눌레와 다쿠아즈를 전문으로 하며, 매일 아침 제품들을 구워냅니다."));
        actions.andExpect(jsonPath("$.body.openingTime").value("07:00"));
        actions.andExpect(jsonPath("$.body.closingTime").value("20:00"));
        actions.andExpect(jsonPath("$.body.closedDay").value("매주 월요일"));
        actions.andExpect(jsonPath("$.body.address").value("부산 부산진구 서전로37번길 20 연의양과"));
        actions.andExpect(jsonPath("$.body.likeCount").value(3));
        actions.andExpect(jsonPath("$.body.latitude").value(35.1587487392983));
        actions.andExpect(jsonPath("$.body.longitude").value(129.064002552455));
        actions.andExpect(jsonPath("$.body.reviewCount").value(2));
        actions.andExpect(jsonPath("$.body.distance").value("356"));
        actions.andExpect(jsonPath("$.body.liked").value(true));
        // actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 매장 상세보기 - 사업자 정보
    @Test
    public void storeBizInfo_test() throws Exception {
        //given
        int storeId = 1;
        //when
        ResultActions actions = mockMvc.perform(
                get("/api/stores/" + storeId + "/biz-info")
                        .header("Authorization", "Bearer " + jwt)
        );
        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.ownerName").value("조정현"));
        actions.andExpect(jsonPath("$.body.ownerTel").value("010-1234-5678"));
        actions.andExpect(jsonPath("$.body.ownerEmail").value("david1234@gmail.com"));
        actions.andExpect(jsonPath("$.body.bizNum").value("123-456-7890"));
        // actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 매장 메뉴보기
    @Test
    public void storeMenuList_test() throws Exception {
        //given
        int storeId = 1;
        //when
        ResultActions actions = mockMvc.perform(
                get("/api/stores/" + storeId + "/menus")
                        .header("Authorization", "Bearer " + jwt)
        );
        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.storeId").value(1));
        actions.andExpect(jsonPath("$.body.storeName").value("연의양과"));
        actions.andExpect(jsonPath("$.body.menuList.[0].id").value(1));
        actions.andExpect(jsonPath("$.body.menuList.[0].category").value("coffee"));
        actions.andExpect(jsonPath("$.body.menuList.[0].name").value("아메리카노"));
        actions.andExpect(jsonPath("$.body.menuList.[0].imgFilename").value("default/americano.png"));
        actions.andExpect(jsonPath("$.body.menuList.[0].description").value("현대인의 필수 카페인"));
        actions.andExpect(jsonPath("$.body.menuList.[0].price").value(3000));
        // actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 매장 메뉴 옵션보기
    @Test
    public void storeMenuDetail_test() throws Exception {
        //given
        int storeId = 1;
        int menuId = 1;
        //when
        ResultActions actions = mockMvc.perform(
                get("/api/stores/" + storeId + "/menus/" + menuId)
                        .header("Authorization", "Bearer " + jwt)
        );
        //then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.storeId").value(1));
        actions.andExpect(jsonPath("$.body.storeName").value("연의양과"));
        actions.andExpect(jsonPath("$.body.menuId").value(1));
        actions.andExpect(jsonPath("$.body.menuName").value("아메리카노"));
        actions.andExpect(jsonPath("$.body.menuImgFilename").value("default/americano.png"));
        actions.andExpect(jsonPath("$.body.menuPrice").value(3000));
        actions.andExpect(jsonPath("$.body.optionList.[0].id").value(1));
        actions.andExpect(jsonPath("$.body.optionList.[0].name").value("아이스"));
        actions.andExpect(jsonPath("$.body.optionList.[0].price").value(0));
        actions.andExpect(jsonPath("$.body.optionList.[0].required").value(true));
        // actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
