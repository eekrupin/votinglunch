package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.data.MenuConsist;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuConsistRepository extends JpaRepository<MenuConsist, Integer> {

    void deleteByDateAndRestaurantAndLunchMenu(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu);

    List<MenuConsist> findAllByDateAndRestaurantAndLunchMenu(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu);

}
