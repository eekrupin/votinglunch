package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.Dish;

import java.util.List;

public interface DishRepository extends AbstractReferenceRepository<Dish> {
    List<Dish> getAllByRestaurant(Integer restaurant_id);
}
