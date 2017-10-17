package com.eekrupin.votinglunch.util;

import com.eekrupin.votinglunch.model.Dish;
import com.eekrupin.votinglunch.repository.datajpa.CrudRestaurantRepository;
import com.eekrupin.votinglunch.to.DishTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DishUtil {

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    public DishUtil() {
    }

    public Dish createNewFromTo(DishTo newEl) {
        return new Dish(null, restaurantRepository.getOne(newEl.getRestaurant_id()), newEl.getDescription());
    }

    public DishTo asTo(Dish el) {
        return new DishTo(el.getId(), el.getRestaurant().getId(), el.getDescription(), el.isDeletionMark());
    }

    public Dish updateFromTo(Dish el, DishTo elTo) {
        el.setDescription(elTo.getDescription());
        el.setRestaurant(restaurantRepository.getOne(elTo.getRestaurant_id()));
        el.setDeletionMark(elTo.isDeletionMark());
        return el;
    }

    public Dish asDish(DishTo el) {
        Dish dish = new Dish(el.getId(), restaurantRepository.getOne(el.getRestaurant_id()), el.getDescription());
        dish.setDeletionMark(el.isDeletionMark());
        return dish;
    }
}
