package com.eekrupin.votinglunch.web.restaurant;

import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.service.BaseService;
import com.eekrupin.votinglunch.web.AbstractBaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdminRestRestaurantController.REST_URL)
public class AdminRestRestaurantController extends AbstractBaseRestController<Restaurant> {
    public static final String REST_URL = AbstractBaseRestController.REST_URL + "/restaurants";

    @Autowired
    public AdminRestRestaurantController(BaseService<Restaurant> service) {
        super(service);
    }
}
