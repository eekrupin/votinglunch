package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.Dish;

import java.util.List;

public interface DishService extends BaseService<Dish> {

    List<Dish> getAllByRestaurant(Integer restaurant_id);

}
