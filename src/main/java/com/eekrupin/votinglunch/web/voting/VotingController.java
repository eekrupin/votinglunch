package com.eekrupin.votinglunch.web.voting;

import com.eekrupin.votinglunch.AuthorizedUser;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.model.data.Voting;
import com.eekrupin.votinglunch.service.VotingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class VotingController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private VotingService service;

    public VotingController(VotingService service) {
        this.service = service;
    }

    public Voting get(LocalDate date){
        log.info("get by date{}, user {}", date.toString(), AuthorizedUser.safeGetIdOrDefaultId() );
        return service.get(date);
    }

    public void delete(LocalDate date) {
        log.info("get by date{}", date.toString(), AuthorizedUser.safeGetIdOrDefaultId() );
        service.delete(date);
    }

    public Voting save(Voting voting) {
        log.info("save voting {}", voting);
        return service.save(voting);
    }

}
