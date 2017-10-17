package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.data.MenuConsist;
import com.eekrupin.votinglunch.repository.interfaces.MenuConsistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class MenuConsistServiceImpl implements MenuConsistService{

    private final MenuConsistRepository repository;

    @Autowired
    public MenuConsistServiceImpl(MenuConsistRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<MenuConsist> save(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu, List<MenuConsist> menuConsists) {
        Assert.notNull(date, "date must not be null");
        Assert.notNull(restaurant, "restaurant must not be null");
        Assert.notNull(lunchMenu, "lunchMenu must not be null");

        delete(date, restaurant, lunchMenu);
        repository.delete(date, restaurant, lunchMenu);
        List<MenuConsist> saved = repository.save(date, restaurant, lunchMenu, menuConsists);
        return saved;
    }

    @Override
    public void delete(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu) {
        Assert.notNull(date, "date must not be null");
        Assert.notNull(restaurant, "restaurant must not be null");
        Assert.notNull(lunchMenu, "lunchMenu must not be null");
        repository.delete(date, restaurant, lunchMenu);
    }

    @Override
    public List<MenuConsist> get(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu) {
        return repository.get(date, restaurant, lunchMenu);
    }
}
