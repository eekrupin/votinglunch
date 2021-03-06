package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.data.MenuConsist;

import java.time.LocalDate;
import java.util.List;

public interface MenuConsistRepository {

    List<MenuConsist> save(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu, List<MenuConsist> menuConsists);

    void delete(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu);

    List<MenuConsist> get(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu);
}
