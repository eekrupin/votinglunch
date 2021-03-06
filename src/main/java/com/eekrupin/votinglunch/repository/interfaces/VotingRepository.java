package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.model.data.Voting;

import java.time.LocalDate;

public interface VotingRepository {

    Voting save(Voting voting);

    boolean delete(LocalDate date, User user);

    Voting get(LocalDate date, User user);

}
