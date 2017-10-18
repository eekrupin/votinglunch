package com.eekrupin.votinglunch.web;

import com.eekrupin.votinglunch.model.data.Voting;
import com.eekrupin.votinglunch.service.VotingService;
import com.eekrupin.votinglunch.service.VotingServiceImpl;
import com.eekrupin.votinglunch.to.VotingTo;
import com.eekrupin.votinglunch.util.VotingUtil;
import com.eekrupin.votinglunch.util.exception.ErrorType;
import com.eekrupin.votinglunch.web.json.JsonUtil;
import com.eekrupin.votinglunch.web.voting.AdminRestVotingController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static com.eekrupin.votinglunch.ReferenceTestData.*;
import static com.eekrupin.votinglunch.TestUtil.userHttpBasic;
import static com.eekrupin.votinglunch.UserTestData.*;
import static java.time.LocalDate.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRestVotingControllerTest extends AbstractControllerTest{

    private static final String REST_URL = AdminRestVotingController.REST_URL;

    @Autowired
    private VotingService baseService;

    @Autowired
    private VotingUtil votingUtil;

    @Test
    public void testGet() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL)
                .param("date", "2017-10-16")
                .with(userHttpBasic(USER)));

        action .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                ;
        VotingTo returnedTo = MATCHER_VOTING.fromJsonAction(action);
        MATCHER_VOTING.assertEquals(VOTING_U1_D1, returnedTo);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testSave() throws Exception {
        LocalDate localDate = of(2017, Month.OCTOBER, 10);

        VotingServiceImpl.setCurrentTimeForTest(LocalTime.of(10, 00));

        VotingTo created = new VotingTo(null, localDate, RESTAURANT_ID2);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(USER))
                )
                .andDo(print())
                .andExpect(status().isOk());

        VotingTo returnedTo = MATCHER_VOTING.fromJsonAction(action);
        MATCHER_VOTING.assertEquals(created, returnedTo);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .param("date", "2017-10-16")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());

        LocalDate localDate = of(2017, Month.OCTOBER, 16);

        boolean hasError = false;
        try {
            Voting voting = baseService.get(localDate);
        }
        catch (Exception e){
            hasError = true;
        }
        Assert.assertTrue(hasError);
    }

    //Невозможно воспроизвести тест! В случае изменения голоса происходит обновление записи.
//    @Test
//    @Transactional(propagation = Propagation.NEVER)
//    public void testWrongSave() throws Exception {
//        LocalDate localDate = of(2017, Month.OCTOBER, 16);
//
//        VotingTo created = new VotingTo(null, localDate, USER_ID, RESTAURANT_ID2);
//        ResultActions action = mockMvc.perform(post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(created))
//                .with(userHttpBasic(USER))
//        )
//                .andDo(print())
//                .andExpect(status().isConflict())
//                .andExpect(errorType(ErrorType.DATA_ERROR))
//                .andExpect(jsonMessage("$.details", ExceptionInfoHandler.EXCEPTION_DUPLICATE_ROW));
//    }


    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdate() throws Exception {
        LocalDate localDate = of(2017, Month.OCTOBER, 16);

        VotingServiceImpl.setCurrentTimeForTest(LocalTime.of(10, 00));

        VotingTo created = new VotingTo(null, localDate, RESTAURANT_ID2);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(USER))
        )
                .andDo(print())
                .andExpect(status().isOk());

        VotingTo returnedTo = MATCHER_VOTING.fromJsonAction(action);
        Voting votingFromDb = baseService.get(localDate);

        Assert.assertEquals(returnedTo.getRestaurant_id(), votingFromDb.getRestaurant().getId());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdateAfterTimeExpired() throws Exception {
        LocalDate localDate = of(2017, Month.OCTOBER, 16);

        VotingServiceImpl.setCurrentTimeForTest(LocalTime.of(11, 30));

        VotingTo created = new VotingTo(null, localDate, RESTAURANT_ID2);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(USER))
        )
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.TIME_EXPIRED))
                .andExpect(jsonMessage("$.details", "exception.common.timeExpired"));

    }

    @Test
    public void testGetDataNotFound() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL)
                .param("date", "2017-10-16")
                .with(userHttpBasic(USER2)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }

}