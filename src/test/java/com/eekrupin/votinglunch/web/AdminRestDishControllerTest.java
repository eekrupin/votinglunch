package com.eekrupin.votinglunch.web;

import com.eekrupin.votinglunch.TestUtil;
import com.eekrupin.votinglunch.service.DishService;
import com.eekrupin.votinglunch.to.DishTo;
import com.eekrupin.votinglunch.util.DishUtil;
import com.eekrupin.votinglunch.util.exception.ErrorType;
import com.eekrupin.votinglunch.web.dish.AdminRestDishController;
import com.eekrupin.votinglunch.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.eekrupin.votinglunch.ReferenceTestData.*;
import static com.eekrupin.votinglunch.TestUtil.userHttpBasic;
import static com.eekrupin.votinglunch.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AdminRestDishControllerTest extends AbstractControllerTest{

    private static final String REST_URL = AdminRestDishController.REST_URL + '/';

    @Autowired
    private DishService baseService;

    @Autowired
    private DishUtil dishUtil;

    @Test
    public void testGet() throws Exception {
        ResultActions actions = mockMvc.perform(get(REST_URL + DISH1_R1_ID)
                .with(userHttpBasic(ADMIN)));

        actions .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                ;
    }

    @Test
    public void testCreate() throws Exception {
        DishTo created = new DishTo(null, RESTAURANT_ID,"New Dish 3 of First restaurant");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(created))
                )
                .andExpect(status().isCreated());

        DishTo returned = MATCHER_DISH.fromJsonAction(action);
        created.setId(returned.getId());

        List<DishTo> all = baseService.getAll().stream().map(el -> dishUtil.asTo(el)).collect(Collectors.toList());

        MATCHER_DISH.assertEquals(created, returned);
        MATCHER_DISH.assertListEquals(Arrays.asList(DISH1_R1, DISH2_R1, DISH1_R2, created), all);
    }

    @Test
    public void testCreateInvalid() throws Exception {
        DishTo expected = new DishTo(null, RESTAURANT_ID, "");
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
    public void testMark() throws Exception {
        mockMvc.perform(put(REST_URL + DISH1_R1_ID + "/mark")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
        )
                .andExpect(status().isOk());

        mockMvc.perform(get(REST_URL + DISH1_R1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.deletionMark").value("true"));
    }

    @Test
    public void testUnMark() throws Exception {
        testMark();
        mockMvc.perform(put(REST_URL + DISH1_R1_ID + "/unmark")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
        )
                .andExpect(status().isOk());

        mockMvc.perform(get(REST_URL + DISH1_R1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.deletionMark").value("false"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_R1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());

        List<DishTo> all = baseService.getAll().stream().map(el -> dishUtil.asTo(el)).collect(Collectors.toList());
        MATCHER_DISH.assertListEquals(Arrays.asList(DISH2_R1, DISH1_R2), all);
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_DISH.contentListMatcher(DISH1_R1, DISH2_R1, DISH1_R2)));
    }

    @Test
    public void testGetAllByRestaurant() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "by?restaurant_id=" + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_DISH.contentListMatcher(DISH1_R1, DISH2_R1)));
    }
}