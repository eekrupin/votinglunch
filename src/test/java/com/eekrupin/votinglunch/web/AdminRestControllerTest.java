package com.eekrupin.votinglunch.web;

import com.eekrupin.votinglunch.model.Role;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.service.UserService;
import com.eekrupin.votinglunch.web.user.AdminRestController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static com.eekrupin.votinglunch.TestUtil.userHttpBasic;
import static com.eekrupin.votinglunch.UserTestData.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AdminRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Autowired
    protected UserService userService;

//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }

    @Test
    public void testCreate() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", Role.ROLE_USER, Role.ROLE_ADMIN);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(jsonWithPassword(newUser, "newPass"))
                )
                .andExpect(status().isCreated());

        User returned = MATCHER.fromJsonAction(action);
        newUser.setId(returned.getId());

        MATCHER.assertEquals(newUser, returned);
        MATCHER.assertListEquals(Arrays.asList(USER, ADMIN, newUser), userService.getAll());
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void mark() throws Exception {
    }

    @Test
    public void unMark() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getByEmail() throws Exception {
    }

}