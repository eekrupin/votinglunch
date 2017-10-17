package com.eekrupin.votinglunch.util;

import com.eekrupin.votinglunch.model.data.MenuConsist;
import com.eekrupin.votinglunch.model.data.Voting;
import com.eekrupin.votinglunch.repository.datajpa.CrudDishRepository;
import com.eekrupin.votinglunch.repository.datajpa.CrudLunchMenuRepository;
import com.eekrupin.votinglunch.repository.datajpa.CrudRestaurantRepository;
import com.eekrupin.votinglunch.repository.datajpa.CrudUserRepository;
import com.eekrupin.votinglunch.to.MenuConsistTo;
import com.eekrupin.votinglunch.to.VotingTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VotingUtil {

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    private CrudUserRepository userRepository;

    public VotingUtil() {
    }

    public Voting createNewFromTo(VotingTo newEl) {
        return new Voting(null,
                            newEl.getDate(),
                            userRepository.getOne(newEl.getUser_id()),
                            restaurantRepository.getOne(newEl.getRestaurant_id()) );
    }

    public VotingTo asTo(Voting el) {
        return new VotingTo(el.getId(), el.getDate(), el.getUser().getId(), el.getRestaurant().getId());
    }

    public Voting updateFromTo(Voting el, VotingTo elTo) {
        el.setDate(elTo.getDate());
        el.setUser(userRepository.getOne(elTo.getUser_id()));
        el.setRestaurant(restaurantRepository.getOne(elTo.getRestaurant_id()));
        return el;
    }

    public Voting asVoting(VotingTo el) {
        Voting voting = new Voting(el.getId(),
                                    el.getDate(),
                                    userRepository.getOne(el.getUser_id()),
                                    restaurantRepository.getOne(el.getRestaurant_id()) );
        return voting;
    }

    public CrudRestaurantRepository getRestaurantRepository() {
        return restaurantRepository;
    }

    public CrudUserRepository getUserRepository() {
        return userRepository;
    }
}
