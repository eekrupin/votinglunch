package com.eekrupin.votinglunch.web;

import com.eekrupin.votinglunch.TestUtil;
import com.eekrupin.votinglunch.model.Dish;
import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.service.DishService;
import com.eekrupin.votinglunch.service.MenuConsistService;
import com.eekrupin.votinglunch.to.DishTo;
import com.eekrupin.votinglunch.to.MenuConsistTo;
import com.eekrupin.votinglunch.util.DishUtil;
import com.eekrupin.votinglunch.util.MenuConsistUtil;
import com.eekrupin.votinglunch.util.exception.ErrorType;
import com.eekrupin.votinglunch.web.dish.AdminRestDishController;
import com.eekrupin.votinglunch.web.json.JsonUtil;
import com.eekrupin.votinglunch.web.menuconsist.AdminRestMenuConsistController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.eekrupin.votinglunch.ReferenceTestData.*;
import static com.eekrupin.votinglunch.TestUtil.userHttpBasic;
import static com.eekrupin.votinglunch.UserTestData.ADMIN;
import static java.time.LocalDate.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AdminRestMenuConsistControllerTest extends AbstractControllerTest{

    private static final String REST_URL = AdminRestMenuConsistController.REST_URL;

    @Autowired
    private MenuConsistService baseService;

    @Autowired
    private MenuConsistUtil menuConsistUtil;

    @Test
    public void testGet() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL)
                .param("date", "2017-10-16")
                .param("restaurant_id", String.valueOf(RESTAURANT_ID))
                .param("lunchMenu_id", String.valueOf(LUNCH_MENU_ID1))
                .param("dish_id", String.valueOf(DISH1_R1_ID))
                .with(userHttpBasic(ADMIN)));

        action .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                ;
        List<MenuConsistTo> returnedTo = JsonUtil.readValues(action.andReturn().getResponse().getContentAsString(), MenuConsistTo.class);
        MATCHER_MENUCONSIST.assertListEquals(Arrays.asList(MENUCONSIST_D1_R1_1, MENUCONSIST_D1_R1_2), returnedTo);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .param("date", "2017-10-16")
                .param("restaurant_id", String.valueOf(RESTAURANT_ID))
                .param("lunchMenu_id", String.valueOf(LUNCH_MENU_ID1))
                .param("dish_id", String.valueOf(DISH1_R1_ID))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());

        LocalDate localDate = of(2017, Month.OCTOBER, 16);
        Restaurant restaurant = menuConsistUtil.getRestaurantRepository().getOne(RESTAURANT_ID);
        LunchMenu lunchMenu = menuConsistUtil.getLunchMenuRepository().getOne(LUNCH_MENU_ID1);
        List<MenuConsistTo> all = baseService.get(localDate, restaurant, lunchMenu).stream().map(el -> menuConsistUtil.asTo(el)).collect(Collectors.toList());

        MATCHER_MENUCONSIST.assertListEquals(Collections.emptyList(), all);
    }

    @Test
    public void testSave() throws Exception {
        LocalDate localDate = of(2017, Month.OCTOBER, 16);

        MenuConsistTo created1 = new MenuConsistTo(null, localDate, RESTAURANT_ID, LUNCH_MENU_ID1, DISH1_R1_ID);
        List<MenuConsistTo> menuConsistsTo = Arrays.asList(created1);

        ResultActions action = mockMvc.perform(post(REST_URL)
                .param("date", "2017-10-16")
                .param("restaurant_id", String.valueOf(RESTAURANT_ID))
                .param("lunchMenu_id", String.valueOf(LUNCH_MENU_ID1))
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(menuConsistsTo))
                )
                .andDo(print())
                .andExpect(status().isOk());

        List<MenuConsistTo> returnedTo = JsonUtil.readValues(action.andReturn().getResponse().getContentAsString(), MenuConsistTo.class);

        returnedTo.forEach(r->menuConsistsTo.stream().filter(c->c.getDish_id().equals(r.getDish_id())).forEach(filtered->filtered.setId(r.getId())));

        Restaurant restaurant = menuConsistUtil.getRestaurantRepository().getOne(RESTAURANT_ID);
        LunchMenu lunchMenu = menuConsistUtil.getLunchMenuRepository().getOne(LUNCH_MENU_ID1);
        List<MenuConsistTo> all = baseService.get(localDate, restaurant, lunchMenu).stream().map(el -> menuConsistUtil.asTo(el)).collect(Collectors.toList());

        MATCHER_MENUCONSIST.assertListEquals(menuConsistsTo, all);
    }

    @Test
    public void testSaveWithWrongFilter() throws Exception {
        LocalDate localDate = of(2017, Month.OCTOBER, 16);

        MenuConsistTo created1 = new MenuConsistTo(null, localDate, RESTAURANT_ID, LUNCH_MENU_ID1, DISH1_R1_ID);
        List<MenuConsistTo> menuConsistsTo = Arrays.asList(created1);

        ResultActions action = mockMvc.perform(post(REST_URL)
                .param("date", "2017-10-15")
                .param("restaurant_id", String.valueOf(RESTAURANT_ID))
                .param("lunchMenu_id", String.valueOf(LUNCH_MENU_ID1))
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(menuConsistsTo))
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}