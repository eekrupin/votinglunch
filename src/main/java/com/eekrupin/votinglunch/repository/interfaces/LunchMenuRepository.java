package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;

import java.util.List;

public interface LunchMenuRepository extends ReferenceRepository<LunchMenu>{
    List<LunchMenu> getAll(Restaurant restaurant);
}
