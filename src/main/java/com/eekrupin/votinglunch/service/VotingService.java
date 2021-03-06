package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.model.data.Voting;
import java.time.LocalDate;

public interface VotingService {

    Voting save(Voting voting);

    boolean delete(LocalDate date);

    Voting get(LocalDate date);

}
