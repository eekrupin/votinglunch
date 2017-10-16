package com.eekrupin.votinglunch.util;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.repository.datajpa.CrudRestaurantRepository;
import com.eekrupin.votinglunch.to.LunchMenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LunchMenuUtil {

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    public LunchMenu createNewFromTo(LunchMenuTo newEl) {
        return new LunchMenu(null, restaurantRepository.getOne(newEl.getRestaurant_id()), newEl.getDescription());
    }

    public LunchMenuTo asTo(LunchMenu el) {
        return new LunchMenuTo(el.getId(), el.getRestaurant().getId(), el.getDescription(), el.isDeletionMark());
    }

    public LunchMenu updateFromTo(LunchMenu el, LunchMenuTo elTo) {
        el.setDescription(elTo.getDescription());
        el.setRestaurant(restaurantRepository.getOne(elTo.getRestaurant_id()));
        el.setDeletionMark(elTo.isDeletionMark());
        return el;
    }

    public LunchMenu asLunchMenu(LunchMenuTo el) {
        LunchMenu lunchMenu = new LunchMenu(el.getId(), restaurantRepository.getOne(el.getRestaurant_id()), el.getDescription());
        lunchMenu.setDeletionMark(el.isDeletionMark());
        return lunchMenu;
    }
}
