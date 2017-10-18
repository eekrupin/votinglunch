package com.eekrupin.votinglunch.web;

import com.eekrupin.votinglunch.model.ReferenceEntity;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.service.BaseService;
import com.eekrupin.votinglunch.util.exception.ErrorType;
import com.eekrupin.votinglunch.web.json.JsonUtil;
import com.eekrupin.votinglunch.web.restaurant.AdminRestRestaurantController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static com.eekrupin.votinglunch.ReferenceTestData.RESTAURANT_ID;
import static com.eekrupin.votinglunch.TestUtil.userHttpBasic;
import static com.eekrupin.votinglunch.ReferenceTestData.*;
import static com.eekrupin.votinglunch.UserTestData.ADMIN;
import static com.eekrupin.votinglunch.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AdminRestRestaurantControllerTest extends AbstractControllerTest{

    private static final String REST_URL = AdminRestRestaurantController.REST_URL + '/';

    @Autowired
    protected BaseService<Restaurant> baseService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(RESTAURANT));
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant created = new Restaurant(null, "New restaurant");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(created))
                )
                .andExpect(status().isCreated());

        ReferenceEntity returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        @SuppressWarnings("unchecked")
        List<ReferenceEntity> all = (List<ReferenceEntity>) (List<?>) baseService.getAll();

        MATCHER.assertEquals(created, returned);
        MATCHER.assertListEquals(Arrays.asList(RESTAURANT, RESTAURANT2, created), all);
    }

    @Test
    public void testCreateInvalid() throws Exception {
        ReferenceEntity expected = (ReferenceEntity)(new Restaurant(null, ""));
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected))
                )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_ID, "First Restaurant");
        updated.setDescription("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, baseService.get(RESTAURANT_ID));
    }

    @Test
    public void testCreateForbidden() throws Exception {
        Restaurant created = new Restaurant(null, "New restaurant");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(created))
        )
                .andExpect(status().isForbidden());

    }

    @Test
    public void testGetByUser() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(RESTAURANT));
    }

}