package shop.project.pathorderserver.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import shop.project.pathorderserver.MyRestDoc;

import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class StoreOwnerControllerTest extends MyRestDoc {
    @Autowired
    private ObjectMapper objectMapper;

    private static MockHttpSession session;

    @BeforeAll
    public static void setUp() throws Exception {
        session = new MockHttpSession();
        SessionStore sessionStore = new SessionStore();
        sessionStore.setId(1);
        sessionStore.setUsername("david1234");
        sessionStore.setOwnerName("조정현");
        sessionStore.setName("연의양과");

        session.setAttribute("sessionStore", sessionStore);
    }

    @AfterAll
    public static void clean() {
        session.clearAttributes();
    }

    @Test
    public void joinAndLogin_test() throws Exception {
        // join (given+when)
        ResultActions joinActions = mockMvc.perform(MockMvcRequestBuilders.post("/stores/join")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "tester")
                .param("password", "1234")
                .param("ownerName", "조정현")
                .param("ownerTel", "전화번호")
                .param("ownerEmail", "이메일")
                .param("bizNum", "사업자번호")
                .param("name", "매장이름")
                .param("tel", "매장전화번호")
                .param("address", "매장주소")
                .param("latitude", "37.566535")
                .param("longitude", "126.977969"));
        // then
        joinActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/stores/login-form"));
        // login (given+when)
        ResultActions loginActions = mockMvc.perform(MockMvcRequestBuilders.post("/stores/login")
                .param("username", "tester")
                .param("password", "1234"));
        // then
        loginActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void getPendingOrderCount_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        // when
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/stores/{storeId}/pending-order-count", storeId)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        // then
        actions.andExpect(jsonPath("$.body").value(4));
    }

    @Test
    public void orders_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        // when
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/stores/{storeId}/orders", storeId)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("orders"));
        // then
        HashMap<String, Object> response = (HashMap<String, Object>) actions.andReturn().getModelAndView().getModel().get("orders");
        actions
                .andExpect(model().attributeExists("orders"));
        List<Object> pendingOrderList = (List<Object>) response.get("pendingOrderList");
        Assertions.assertThat(pendingOrderList.size()).isEqualTo(5);
        List<Object> preparingOrderList = (List<Object>) response.get("preparingOrderList");
        Assertions.assertThat(preparingOrderList.size()).isEqualTo(0);
        List<Object> preparedOrderList = (List<Object>) response.get("preparedOrderList");
        Assertions.assertThat(preparedOrderList.size()).isEqualTo(0);
    }

    @Test
    public void updateOrder_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        int orderId = 8;
        // when
        ResultActions updateActions = mockMvc.perform(MockMvcRequestBuilders.post("/stores/{storeId}/orders/{orderId}/update", storeId, orderId)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .session(session)
                .param("status", "PENDING"));
        // then
        updateActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/stores/" + storeId + "/orders"));
        // when
        ResultActions getActions = mockMvc.perform(MockMvcRequestBuilders.get("/stores/{storeId}/orders", storeId)
                .session(session));
        // then
        HashMap<String, Object> response = (HashMap<String, Object>) getActions
                .andExpect(status().isOk())
                .andExpect(view().name("orders"))
                .andReturn()
                .getModelAndView()
                .getModel()
                .get("orders");
        List<Object> pendingOrderList = (List<Object>) response.get("pendingOrderList");
        Assertions.assertThat(pendingOrderList.size()).isEqualTo(4);
        List<Object> preparingOrderList = (List<Object>) response.get("preparingOrderList");
        Assertions.assertThat(preparingOrderList.size()).isEqualTo(1);
    }

    @Test
    public void orderDetail_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        int orderId = 8;
        // when
        // then
    }

    @Test
    public void orderList_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        // when
        // then
    }

    @Test
    public void orderListSortByDate_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        String startDate = "2024-01-01";
        String endDate = "2024-05-20";
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/stores/{storeId}/orders/history/date", storeId)
                        .param("startDate", startDate)
                        .param("endDate", endDate)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // then
    }

    @Test
    public void menuList_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        // when
        // then
    }

    @Test
    public void menuDetail_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        int menuId = 1; // Assuming a menuId of 1 exists
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/stores/{storeId}/menus/{menuId}", storeId, menuId)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // then
    }

    @Test
    public void addMenu_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        // when
        /*
        mockMvc.perform(MockMvcRequestBuilders.post("/stores/{storeId}/menus", storeId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", createMenuDTO.getName())
                        .param("price", String.valueOf(createMenuDTO.getPrice())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/stores/" + storeId + "/menus"));
        */
        // then
    }

    @Test
    public void updateMenu_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        int menuId = 1; // Assuming a menuId of 1 exists
        StoreRequest.UpdateMenuDTO updateMenuDTO = new StoreRequest.UpdateMenuDTO();
        // Set properties for updateMenuDTO

        // when
        mockMvc.perform(MockMvcRequestBuilders.put("/stores/{storeId}/menus/{menuId}", storeId, menuId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateMenuDTO))
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // then
    }

    @Test
    public void detail_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/stores/{storeId}", storeId)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("store"));
        // then
    }

    @Test
    public void updateForm_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/stores/{storeId}/update-form", storeId)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("store-update-form"));
        // then
    }

    @Test
    public void update_test() throws Exception {
        // given
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        int storeId = sessionStore.getId();
        // when
        /*
        mockMvc.perform(MockMvcRequestBuilders.post("/stores/{storeId}", storeId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", updateDTO.getName())
                        .param("address", updateDTO.getAddress()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/stores/" + storeId));
        */
        // then
    }
}