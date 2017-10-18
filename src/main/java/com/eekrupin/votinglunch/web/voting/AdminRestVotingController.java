package com.eekrupin.votinglunch.web.voting;

import com.eekrupin.votinglunch.View;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.model.data.Voting;
import com.eekrupin.votinglunch.service.VotingService;
import com.eekrupin.votinglunch.to.VotingTo;
import com.eekrupin.votinglunch.util.VotingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(AdminRestVotingController.REST_URL)
public class AdminRestVotingController extends VotingController {
    public static final String REST_URL = "/rest/voting";

    @Autowired
    private VotingUtil votingUtil;

    @Autowired
    public AdminRestVotingController(VotingService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VotingTo createWithLocation(@Validated(View.ValidatedRestUI.class) @RequestBody VotingTo element) {
        Voting voting = votingUtil.createNewFromTo(element);
        Voting created = super.save(voting);
        return votingUtil.asTo(created);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public VotingTo getVoting(@RequestParam(value = "date") LocalDate date) {
        Voting voting = super.get(date);
        return votingUtil.asTo(voting);
    }

    @DeleteMapping(value = "")
    public void delete(@RequestParam(value = "date") LocalDate date) {
        super.delete(date);
    }

}
