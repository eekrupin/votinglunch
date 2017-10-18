package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.AuthorizedUser;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.model.data.Voting;
import com.eekrupin.votinglunch.repository.datajpa.CrudUserRepository;
import com.eekrupin.votinglunch.repository.interfaces.UserRepository;
import com.eekrupin.votinglunch.repository.interfaces.VotingRepository;
import com.eekrupin.votinglunch.util.DateUtil;
import com.eekrupin.votinglunch.util.UserUtil;
import com.eekrupin.votinglunch.util.exception.TimeExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.eekrupin.votinglunch.util.ValidationUtil.CheckUserOnAuthority;
import static com.eekrupin.votinglunch.util.ValidationUtil.checkNotFound;

@Service
public class VotingServiceImpl implements VotingService{

    private final VotingRepository repository;
    private final CrudUserRepository userRepository;

    private static final LocalTime expirationTimeVoting = UserUtil.getExpirationTimeVoting();
    private static LocalTime currentTimeForTest = null;

    @Autowired
    public VotingServiceImpl(VotingRepository repository, CrudUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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
        return repository.save(voting);
    }

    @Override
    public boolean delete(LocalDate date) {
        Assert.notNull(date, "date must not be null");       ;
        return repository.delete( date, userRepository.getOne(AuthorizedUser.safeGetIdOrDefaultId()) );
    }

    @Override
    public Voting get(LocalDate date) {
        User user = userRepository.getOne(AuthorizedUser.safeGetIdOrDefaultId());
        return checkNotFound(repository.get(date, userRepository.getOne(AuthorizedUser.safeGetIdOrDefaultId()) ), String.format("date: %s, user: %s", DateUtil.toString(date), user.getId()));
    }


    public static void setCurrentTimeForTest(LocalTime currentTimeForTest) {
        VotingServiceImpl.currentTimeForTest = currentTimeForTest;
    }

    public static LocalTime getCurrentTimeForTest() {
        return currentTimeForTest;
    }
}
