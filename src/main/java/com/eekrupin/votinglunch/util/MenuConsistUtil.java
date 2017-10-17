package com.eekrupin.votinglunch.util;

import com.eekrupin.votinglunch.model.data.MenuConsist;
import com.eekrupin.votinglunch.repository.datajpa.CrudDishRepository;
import com.eekrupin.votinglunch.repository.datajpa.CrudLunchMenuRepository;
import com.eekrupin.votinglunch.repository.datajpa.CrudRestaurantRepository;
import com.eekrupin.votinglunch.to.MenuConsistTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuConsistUtil {

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    private CrudLunchMenuRepository lunchMenuRepository;

    @Autowired
    private CrudDishRepository dishRepository;

    public MenuConsistUtil() {
    }

    public MenuConsist createNewFromTo(MenuConsistTo newEl) {
        return new MenuConsist(null,
                                newEl.getDate(),
                                restaurantRepository.getOne(newEl.getRestaurant_id()),
                                lunchMenuRepository.getOne(newEl.getLunchMenu_id()),
                                dishRepository.getOne(newEl.getDish_id()));
    }

    public MenuConsistTo asTo(MenuConsist el) {
        return new MenuConsistTo(el.getId(), el.getDate(), el.getRestaurant().getId(), el.getLunchMenu().getId(), el.getDish().getId());
    }

    public MenuConsist updateFromTo(MenuConsist el, MenuConsistTo elTo) {
        el.setDate(elTo.getDate());
        el.setRestaurant(restaurantRepository.getOne(elTo.getRestaurant_id()));
        el.setLunchMenu(lunchMenuRepository.getOne(elTo.getLunchMenu_id()));
        el.setDish(dishRepository.getOne(elTo.getDish_id()));
        return el;
    }

    public MenuConsist asMenuConsist(MenuConsistTo el) {
        MenuConsist menuConsist = new MenuConsist(el.getId(),
                                                el.getDate(),
                                                restaurantRepository.getOne(el.getRestaurant_id()),
                                                lunchMenuRepository.getOne(el.getLunchMenu_id()),
                                                dishRepository.getOne(el.getDish_id()));
        return menuConsist;
    }

    public CrudRestaurantRepository getRestaurantRepository() {
        return restaurantRepository;
    }

    public CrudLunchMenuRepository getLunchMenuRepository() {
        return lunchMenuRepository;
    }

    public CrudDishRepository getDishRepository() {
        return dishRepository;
    }
}
