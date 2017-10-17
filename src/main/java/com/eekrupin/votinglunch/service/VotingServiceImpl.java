package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.AuthorizedUser;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.model.data.Voting;
import com.eekrupin.votinglunch.repository.interfaces.VotingRepository;
import com.eekrupin.votinglunch.util.DateUtil;
import com.eekrupin.votinglunch.util.UserUtil;
import com.eekrupin.votinglunch.util.exception.ForbiddenException;
import com.eekrupin.votinglunch.util.exception.TimeExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.eekrupin.votinglunch.util.ValidationUtil.checkNotFound;

@Service
public class VotingServiceImpl implements VotingService{

    private final VotingRepository repository;

    private static final LocalTime expirationTimeVoting = UserUtil.getExpirationTimeVoting();
    private static LocalTime currentTimeForTest = null;

    @Autowired
    public VotingServiceImpl(VotingRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Voting save(Voting voting) {
        Assert.notNull(voting, "voting must not be null");

        LocalTime time = currentTimeForTest == null ? LocalTime.now() : getCurrentTimeForTest();
        if (time.isAfter(expirationTimeVoting)) {
            throw new TimeExpiredException("Current time: " + LocalTime.now().toString());
        }

        Voting votingFromDb = repository.get(voting.getDate(), voting.getUser());
        if (votingFromDb!=null){
            voting.setId(votingFromDb.getId());
        }
        //delete(voting.getDate(), voting.getUser());
        return repository.save(voting);
    }

    @Override
    public boolean delete(LocalDate date, User user) {
        Assert.notNull(date, "date must not be null");
        Assert.notNull(user, "user must not be null");
        return repository.delete(date, user);
    }

    @Override
    public Voting get(LocalDate date, User user) {
        //TODO change this
        if ( !user.getId().equals(AuthorizedUser.id()) ){
            throw new ForbiddenException();
        }
        return checkNotFound(repository.get(date, user), String.format("date: %s, user: %s", DateUtil.toString(date), user.getId()));
    }

    public static void setCurrentTimeForTest(LocalTime currentTimeForTest) {
        VotingServiceImpl.currentTimeForTest = currentTimeForTest;
    }

    public static LocalTime getCurrentTimeForTest() {
        return currentTimeForTest;
    }
}
