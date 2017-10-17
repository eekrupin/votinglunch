package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.model.data.Voting;
import com.eekrupin.votinglunch.repository.interfaces.VotingRepository;
import com.eekrupin.votinglunch.util.DateUtil;
import com.eekrupin.votinglunch.util.exception.WrongFilterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.eekrupin.votinglunch.util.ValidationUtil.checkNotFound;

@Repository
public class DataJpaVotingRepositoryImpl implements VotingRepository {

    private CrudVotingRepository crudVotingRepository;

    @Autowired
    public DataJpaVotingRepositoryImpl(CrudVotingRepository crudVotingRepository) {
        this.crudVotingRepository = crudVotingRepository;
    }

    @Override
    @Transactional
    public Voting save(Voting voting) {
        return crudVotingRepository.save(voting);
    }

    @Override
    @Transactional
    public boolean delete(LocalDate date, User user) {
        checkDateUser(date, user);
        return crudVotingRepository.deleteByDateAndUser(date, user)!=0;
    }

    @Override
    public Voting get(LocalDate date, User user) {
        checkDateUser(date, user);
        //return checkNotFound(crudVotingRepository.getByDateAndUser(date, user), String.format("date: %s, user: %s", DateUtil.toString(date), user.getId()));
        return crudVotingRepository.getByDateAndUser(date, user);
    }

    private void checkDateUser(LocalDate date, User user) {
            boolean valid = date != null && user != null ;
            if (!valid){
                throw new WrongFilterException("Params for entry voting is invalid");
            }
    }

}
