package com.eekrupin.votinglunch.web;

import com.eekrupin.votinglunch.TestUtil;
import com.eekrupin.votinglunch.service.LunchMenuService;
import com.eekrupin.votinglunch.to.LunchMenuTo;
import com.eekrupin.votinglunch.util.LunchMenuUtil;
import com.eekrupin.votinglunch.util.exception.ErrorType;
import com.eekrupin.votinglunch.web.json.JsonUtil;
import com.eekrupin.votinglunch.web.lunchmenu.AdminRestLunchMenuController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.eekrupin.votinglunch.ReferenceTestData.*;
import static com.eekrupin.votinglunch.TestUtil.userHttpBasic;
import static com.eekrupin.votinglunch.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class AdminRestLunchMenuControllerTest extends AbstractControllerTest{

    private static final String REST_URL = AdminRestLunchMenuController.REST_URL + '/';

    @Autowired
    private LunchMenuService baseService;

    @Autowired
    private LunchMenuUtil lunchMenuUtil;

    @Test
    public void testGet() throws Exception {
        ResultActions actions = mockMvc.perform(get(REST_URL + LUNCH_MENU_ID1)
                .with(userHttpBasic(ADMIN)));

        actions .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                ;
    }

    @Test
    public void testCreate() throws Exception {
        LunchMenuTo created = new LunchMenuTo(null, RESTAURANT_ID,"New menu 1 restaurant");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(created))
                )
                .andExpect(status().isCreated())
                .andDo(print());

        LunchMenuTo returned = MATCHER_MENU.fromJsonAction(action);
        created.setId(returned.getId());

        List<LunchMenuTo> all = baseService.getAll().stream().map(el -> lunchMenuUtil.asTo(el)).collect(Collectors.toList());

        MATCHER_MENU.assertEquals(created, returned);
        MATCHER_MENU.assertListEquals(Arrays.asList(LUNCH_MENU1, LUNCH_MENU2, created), all);
    }

    @Test
    public void testCreateInvalid() throws Exception {
        LunchMenuTo expected = new LunchMenuTo(null, RESTAURANT_ID, "");
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
        LunchMenuTo updated = new LunchMenuTo(LUNCH_MENU_ID1, RESTAURANT_ID, "New menu");
        ResultActions action = mockMvc.perform(put(REST_URL + LUNCH_MENU_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated))
                )
                .andExpect(status().isOk())
                .andDo(print());

        MATCHER_MENU.assertEquals( updated, lunchMenuUtil.asTo(baseService.get(LUNCH_MENU_ID1)) );

    }

    @Test
    public void testMark() throws Exception {
        mockMvc.perform(put(REST_URL + LUNCH_MENU_ID1 + "/mark")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
        )
                .andExpect(status().isOk());

        mockMvc.perform(get(REST_URL + LUNCH_MENU_ID1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.deletionMark").value("true"));
    }

    @Test
    public void testUnMark() throws Exception {
        testMark();
        mockMvc.perform(put(REST_URL + LUNCH_MENU_ID1 + "/unmark")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
        )
                .andExpect(status().isOk());

        mockMvc.perform(get(REST_URL + LUNCH_MENU_ID1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.deletionMark").value("false"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + LUNCH_MENU_ID1)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());

        List<LunchMenuTo> all = baseService.getAll().stream().map(el -> lunchMenuUtil.asTo(el)).collect(Collectors.toList());
        MATCHER_MENU.assertListEquals(Collections.singletonList(LUNCH_MENU2), all);
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_MENU.contentListMatcher(LUNCH_MENU1, LUNCH_MENU2)))
                .andDo(print());
    }

    @Test
    public void testGetAllByRestaurant() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "by?restaurant_id=" + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_MENU.contentListMatcher(LUNCH_MENU1)))
        .andDo(print());
    }
}