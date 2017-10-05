package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.data.MenuConsist;

import java.time.LocalDate;
import java.util.List;

public interface MenuConsistRepository {
    MenuConsist save(MenuConsist menuConsist);

    boolean delete(int id);

    MenuConsist get(int id);

    List<MenuConsist> getByDateAndRestaurantAndLunchMenu(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu);
}
