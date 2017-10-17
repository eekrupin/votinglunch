package com.eekrupin.votinglunch.web.menuconsist;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.data.MenuConsist;
import com.eekrupin.votinglunch.service.MenuConsistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class MenuConsistController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private MenuConsistService service;

    public MenuConsistController(MenuConsistService service) {
        this.service = service;
    }

    public List<MenuConsist> get(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu){
        log.info("get by date{}, restaurant {}, lunchMenu{}", date.toString(), restaurant.getId(), lunchMenu.getId());
        return service.get(date, restaurant, lunchMenu);
    }

    public void delete(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu) {
        log.info("delete by date{}, restaurant {}, lunchMenu{}", date.toString(), restaurant.getId(), lunchMenu.getId());
        service.delete(date, restaurant, lunchMenu);
    }

    public List<MenuConsist> save(LocalDate date, Restaurant restaurant, LunchMenu lunchMenu, List<MenuConsist> menuConsists) {
        log.info("save by date{}, restaurant {}, lunchMenu{}, count {}", date.toString(), restaurant.getId(), lunchMenu.getId(), menuConsists.size());
        return service.save(date, restaurant, lunchMenu, menuConsists);
    }

}
