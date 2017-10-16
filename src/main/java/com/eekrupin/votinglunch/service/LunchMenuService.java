package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.LunchMenu;

import java.util.List;

public interface LunchMenuService extends BaseService<LunchMenu> {

    List<LunchMenu> getAllByRestaurant(Integer restaurant_id);

}
