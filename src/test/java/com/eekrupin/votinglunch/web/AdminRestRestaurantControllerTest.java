package com.eekrupin.votinglunch.web;

import com.eekrupin.votinglunch.TestUtil;
import com.eekrupin.votinglunch.model.ReferenceEntity;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.Role;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.service.BaseService;
import com.eekrupin.votinglunch.util.exception.ErrorType;
import com.eekrupin.votinglunch.web.json.JsonUtil;
import com.eekrupin.votinglunch.web.restaurant.AdminRestRestaurantController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.eekrupin.votinglunch.ReferenceTestData.RESTAURANT_ID;
import static com.eekrupin.votinglunch.TestUtil.userHttpBasic;
import static com.eekrupin.votinglunch.ReferenceTestData.*;
import static com.eekrupin.votinglunch.UserTestData.ADMIN;
import static com.eekrupin.votinglunch.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL;
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
        ReferenceEntity created = (ReferenceEntity)(new Restaurant(null, "New restaurant"));
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
        MATCHER.assertListEquals(Arrays.asList(RESTAURANT, created), all);
    }
//
//    @Test
//    public void testCreateInvalid() throws Exception {
//        String password = "newPass";
//        User expected = new User(null, null, "", password, Role.ROLE_USER, Role.ROLE_ADMIN);
//        ResultActions action = mockMvc.perform(post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(ADMIN))
//                .content(JsonUtil.writeValue(expected))
//                .content(jsonWithPassword(expected, password)))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
//                .andDo(print());
//    }
//
//    @Test
//    @Transactional(propagation = Propagation.NEVER)
//    public void testCreateDuplicate() throws Exception {
//        User expected = new User(null, "New", "user@yandex.ru", "newPass", Role.ROLE_USER, Role.ROLE_ADMIN);
//        mockMvc.perform(post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(ADMIN))
//                .content(jsonWithPassword(expected,"newPass")))
//                .andExpect(status().isConflict())
//                .andExpect(errorType(ErrorType.DATA_ERROR))
//                .andExpect(jsonMessage("$.details", EXCEPTION_DUPLICATE_EMAIL))
//                .andDo(print());
//
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        User updated = new User(USER);
//        updated.setDescription("UpdatedName");
//        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
//        mockMvc.perform(put(REST_URL + USER_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(ADMIN))
//                .content(jsonWithPassword(updated, "password")))
//                .andExpect(status().isOk());
//
//        MATCHER.assertEquals(updated, baseService.get(USER_ID));
//    }
//
//    @Test
//    public void testUpdateInvalid() throws Exception {
//        User updated = new User(USER);
//        updated.setDescription("");
//        mockMvc.perform(put(REST_URL + USER_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(ADMIN))
//                .content(JsonUtil.writeValue(updated))
//                .content(jsonWithPassword(updated, "password")))
//                .andExpect(status().isUnprocessableEntity())
//                .andDo(print())
//                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
//                .andDo(print());
//    }
//
//    @Test
//    @Transactional(propagation = Propagation.NEVER)
//    public void testUpdateDuplicate() throws Exception {
//        User updated = new User(USER);
//        updated.setEmail("admin@gmail.com");
//        mockMvc.perform(put(REST_URL + USER_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(ADMIN))
//                .content(jsonWithPassword(updated, "password")))
//                .andExpect(status().isConflict())
//                .andExpect(errorType(ErrorType.DATA_ERROR))
//                .andExpect(jsonMessage("$.details", EXCEPTION_DUPLICATE_EMAIL))
//                .andDo(print());
//    }
//
//    @Test
//    public void testGetAll() throws Exception {
//        TestUtil.print(mockMvc.perform(get(REST_URL)
//                .with(userHttpBasic(ADMIN)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MATCHER.contentListMatcher(USER, ADMIN)));
//    }
//
//    @Test
//    public void testGetNotFound() throws Exception {
//        mockMvc.perform(get(REST_URL + 1)
//                .with(userHttpBasic(ADMIN)))
//                .andExpect(status().isUnprocessableEntity())
//                .andDo(print());
//    }
//
//    @Test
//    public void testMark() throws Exception {
//        mockMvc.perform(put(REST_URL + USER_ID + "/mark")
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(ADMIN))
//                )
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get(REST_URL + USER_ID)
//                .with(userHttpBasic(ADMIN)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.deletionMark").value("true"));
//    }
//
//    @Test
//    public void testUnMark() throws Exception {
//        testMark();
//        mockMvc.perform(put(REST_URL + USER_ID + "/unmark")
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(ADMIN))
//                )
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get(REST_URL + USER_ID)
//                .with(userHttpBasic(ADMIN)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.deletionMark").value("false"));
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        mockMvc.perform(delete(REST_URL + USER_ID)
//                .with(userHttpBasic(ADMIN)))
//                .andDo(print())
//                .andExpect(status().isOk());
//        MATCHER.assertListEquals(Collections.singletonList(ADMIN), baseService.getAll());
//    }
//
//    @Test
//    public void testDeleteNotFound() throws Exception {
//        mockMvc.perform(delete(REST_URL + 1)
//                .with(userHttpBasic(ADMIN)))
//                .andExpect(status().isUnprocessableEntity())
//                .andDo(print());
//    }
//
//    @Test
//    public void testGetUnauth() throws Exception {
//        mockMvc.perform(get(REST_URL))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void testGetForbidden() throws Exception {
//        mockMvc.perform(get(REST_URL)
//                .with(userHttpBasic(USER)))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void testGetByEmail() throws Exception {
//        mockMvc.perform(get(REST_URL + "by?email=" + ADMIN.getEmail())
//                .with(userHttpBasic(ADMIN)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MATCHER.contentMatcher(ADMIN));
//    }

}