package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.model.data.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface CrudVotingRepository extends JpaRepository<Voting, Integer> {

    int deleteByDateAndUser(LocalDate date, User user);

    Voting getByDateAndUser(LocalDate date, User user);

}
