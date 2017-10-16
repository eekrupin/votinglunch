package com.eekrupin.votinglunch.web.dish;

import com.eekrupin.votinglunch.model.Dish;
import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.service.DishService;
import com.eekrupin.votinglunch.web.AbstractBaseController;

import java.util.List;

public class ExtendDishController extends AbstractBaseController<Dish> {

    private DishService service;

    public ExtendDishController(DishService service) {
        super(service);
        this.service = service;
    }

    List<Dish> getAllByRestaurant(Integer restaurant_id){
        log.info("getAllByRestaurant {}", restaurant_id);
        return service.getAllByRestaurant(restaurant_id);
    }

}
