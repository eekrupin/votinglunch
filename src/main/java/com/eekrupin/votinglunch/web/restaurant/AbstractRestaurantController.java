package com.eekrupin.votinglunch.web.restaurant;

import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.service.BaseService;
import com.eekrupin.votinglunch.service.RestaurantServiceImpl;
import com.eekrupin.votinglunch.service.UserService;
import com.eekrupin.votinglunch.web.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractRestaurantController extends AbstractBaseController<Restaurant> {

    private BaseService<Restaurant> service;

    @Autowired
    public AbstractRestaurantController(BaseService<Restaurant> service) {
        super(service);
    }

}
