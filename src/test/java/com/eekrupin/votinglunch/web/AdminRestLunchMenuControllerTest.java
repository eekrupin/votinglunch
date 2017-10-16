package com.eekrupin.votinglunch.web;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.ReferenceEntity;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.service.LunchMenuService;
import com.eekrupin.votinglunch.to.LunchMenuTo;
import com.eekrupin.votinglunch.util.LunchMenuUtil;
import com.eekrupin.votinglunch.util.exception.ErrorType;
import com.eekrupin.votinglunch.web.json.JsonUtil;
import com.eekrupin.votinglunch.web.lunchmenu.AdminRestLunchMenuController;
import org.junit.Assert;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
                .andExpect(status().isCreated());

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
}