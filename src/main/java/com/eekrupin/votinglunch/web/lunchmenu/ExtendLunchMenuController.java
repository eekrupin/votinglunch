package com.eekrupin.votinglunch.web.lunchmenu;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.service.BaseService;
import com.eekrupin.votinglunch.service.LunchMenuService;
import com.eekrupin.votinglunch.web.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExtendLunchMenuController extends AbstractBaseController<LunchMenu> {

    private LunchMenuService service;

    public ExtendLunchMenuController(LunchMenuService service) {
        super(service);
        this.service = service;
    }

    List<LunchMenu> getAllByRestaurant(Integer restaurant_id){
        log.info("getAllByRestaurant {}", restaurant_id);
        return service.getAllByRestaurant(restaurant_id);
    }

}
