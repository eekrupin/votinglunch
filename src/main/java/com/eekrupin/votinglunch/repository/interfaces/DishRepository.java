package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.Dish;
import com.eekrupin.votinglunch.model.LunchMenu;

import java.util.List;

public interface DishRepository extends ReferenceRepository<Dish>{
    List<Dish> getAll(LunchMenu lunchMenu);
}
