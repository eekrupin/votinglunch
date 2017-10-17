package com.eekrupin.votinglunch.web.menuconsist;

import com.eekrupin.votinglunch.View;
import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.data.MenuConsist;
import com.eekrupin.votinglunch.service.MenuConsistService;
import com.eekrupin.votinglunch.to.MenuConsistTo;
import com.eekrupin.votinglunch.util.MenuConsistUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AdminRestMenuConsistController.REST_URL)
public class AdminRestMenuConsistController extends MenuConsistController {
    public static final String REST_URL = "/rest/admin/menuconsist";

    @Autowired
    private MenuConsistUtil menuConsistUtil;

    @Autowired
    public AdminRestMenuConsistController(MenuConsistService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuConsistTo> createWithLocation(
                                    @RequestParam(value = "date") LocalDate date,
                                    @RequestParam(value = "restaurant_id") Integer restaurant_id,
                                    @RequestParam(value = "lunchMenu_id") Integer lunchMenu_id,
                                    @Validated(View.ValidatedRestUI.class) @RequestBody List<MenuConsistTo> elements) {
        List<MenuConsist> collects = elements.stream().map(el -> menuConsistUtil.createNewFromTo(el)).collect(Collectors.toList());
        Restaurant restaurant = menuConsistUtil.getRestaurantRepository().getOne(restaurant_id);
        LunchMenu lunchMenu = menuConsistUtil.getLunchMenuRepository().getOne(lunchMenu_id);
        List<MenuConsist> created = super.save(date, restaurant, lunchMenu, collects);

        @SuppressWarnings("UnnecessaryLocalVariable")
        List<MenuConsistTo> createdTo = created.stream().map(el -> menuConsistUtil.asTo(el)).collect(Collectors.toList());
        return createdTo;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuConsistTo> get(
                            @RequestParam(value = "date") LocalDate date,
                            @RequestParam(value = "restaurant_id") Integer restaurant_id,
                            @RequestParam(value = "lunchMenu_id") Integer lunchMenu_id) {
        Restaurant restaurant = menuConsistUtil.getRestaurantRepository().getOne(restaurant_id);
        LunchMenu lunchMenu = menuConsistUtil.getLunchMenuRepository().getOne(lunchMenu_id);
        List<MenuConsist> menuConsists = super.get(date, restaurant, lunchMenu);

        @SuppressWarnings("UnnecessaryLocalVariable")
        List<MenuConsistTo> menuConsistsTo = menuConsists.stream().map(el -> menuConsistUtil.asTo(el)).collect(Collectors.toList());
        return menuConsistsTo;
    }

    @DeleteMapping(value = "")
    public void delete(@RequestParam(value = "date") LocalDate date,
                       @RequestParam(value = "restaurant_id") Integer restaurant_id,
                       @RequestParam(value = "lunchMenu_id") Integer lunchMenu_id) {
        Restaurant restaurant = menuConsistUtil.getRestaurantRepository().getOne(restaurant_id);
        LunchMenu lunchMenu = menuConsistUtil.getLunchMenuRepository().getOne(lunchMenu_id);
        super.delete(date, restaurant, lunchMenu);
    }

}
