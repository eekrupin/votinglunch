package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.data.MenuConsist;
import com.eekrupin.votinglunch.model.data.Voting;

import java.time.LocalDate;
import java.util.List;

public interface VotingRepository {
    Voting save(Voting voting);

    boolean delete(int id);

    Voting get(int id);

    List<Voting> getByDateRestaurant(LocalDate date, Restaurant restaurant);
}
