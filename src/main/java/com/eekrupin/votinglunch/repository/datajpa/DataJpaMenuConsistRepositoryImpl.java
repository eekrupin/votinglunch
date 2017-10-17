package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.data.MenuConsist;
import com.eekrupin.votinglunch.repository.interfaces.MenuConsistRepository;
import com.eekrupin.votinglunch.util.exception.WrongFilterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMenuConsistRepositoryImpl implements MenuConsistRepository{

    private CrudMenuConsistRepository crudMenuConsistRepository;

    @Autowired
    public DataJpaMenuConsistRepositoryImpl(CrudMenuConsistRepository crudMenuConsistRepository) {
        this.crudMenuConsistRepository = crudMenuConsistRepository;
    }

    @Override
    @Transactional
    public List<MenuConsist> save(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu, List<MenuConsist> menuConsists) {
        checkEntryFilter(date, restaurant, lunchMenu, menuConsists);
        List<MenuConsist> saved = crudMenuConsistRepository.save(menuConsists);

        return saved;
    }

    @Override
    @Transactional
    public void delete(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu) {
        crudMenuConsistRepository.deleteByDateAndRestaurantAndLunchMenu(date, restaurant, lunchMenu);
    }

    @Override
    public List<MenuConsist> get(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu) {
        return crudMenuConsistRepository.findAllByDateAndRestaurantAndLunchMenu(date, restaurant, lunchMenu);
    }

    private void checkEntryFilter(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu, List<MenuConsist> menuConsists) {
        for (MenuConsist menuConsist:menuConsists) {
            boolean filterValid = menuConsist.getDate().equals(date) && menuConsist.getRestaurant().getId().equals(restaurant.getId()) && menuConsist.getLunchMenu().getId().equals(lunchMenu.getId());
            if (!filterValid){
                throw new WrongFilterException("Filter for entry is invalid");
            }
        }
    }

}
