package com.eekrupin.votinglunch.web.dish;

import com.eekrupin.votinglunch.View;
import com.eekrupin.votinglunch.model.Dish;
import com.eekrupin.votinglunch.service.DishService;
import com.eekrupin.votinglunch.to.DishTo;
import com.eekrupin.votinglunch.util.DishUtil;
import com.eekrupin.votinglunch.web.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AdminRestDishController.REST_URL)
public class AdminRestDishController extends AbstractBaseController<Dish> {
    public static final String REST_URL = "/rest/admin/dish";

    private ExtendDishController extendController;

    @Autowired
    private DishUtil dishUtil;

    @Autowired
    public AdminRestDishController(DishService service) {
        super(service);
        extendController = new ExtendDishController(service);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishTo> getAllByRestaurant(@RequestParam("restaurant_id") Integer restaurant_id) {
        return extendController.getAllByRestaurant(restaurant_id).stream().map(el -> dishUtil.asTo(el)).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishTo> createWithLocation(@Validated(View.ValidatedRestUI.class) @RequestBody DishTo el) {
        Dish newDish = dishUtil.createNewFromTo(el);
        Dish created = super.create(newDish);
        DishTo createdTo = dishUtil.asTo(created);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdTo.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(createdTo);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.ValidatedRestUI.class) @RequestBody DishTo el, @PathVariable("id") int id) {
        super.update(dishUtil.asDish(el), id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DishTo getTo(@PathVariable("id") int id) {
        return dishUtil.asTo(super.get(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishTo> getAllTo() {
        return super.getAll().stream().map(el -> dishUtil.asTo(el)).collect(Collectors.toList());
    }


    @PutMapping(value = "/{id}/mark")
    public void mark(@PathVariable("id") int id) {
        Dish el = super.get(id);
        super.mark(el);
    }

    @PutMapping(value = "/{id}/unmark")
    public void unMark(@PathVariable("id") int id) {
        Dish el = super.get(id);
        super.unMark(el);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

}
