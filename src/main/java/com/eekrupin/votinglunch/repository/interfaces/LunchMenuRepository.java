package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.LunchMenu;

import java.util.List;

public interface LunchMenuRepository extends AbstractReferenceRepository<LunchMenu> {
    List<LunchMenu> getAllByRestaurant(Integer restaurant_id);
}
