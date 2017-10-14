package com.eekrupin.votinglunch.web.restaurant;

import com.eekrupin.votinglunch.View;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AdminRestRestaurantController.REST_URL)
public class AdminRestRestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    public AdminRestRestaurantController(BaseService<Restaurant> service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Validated(View.ValidatedRestUI.class) @RequestBody Restaurant el){
        Restaurant created = super.create(el);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.ValidatedRestUI.class) @RequestBody Restaurant el, @PathVariable("id") int id) {
        super.update(el, id);
    }

    @PutMapping(value = "/{id}/mark")
    public void mark(@PathVariable("id") int id) {
        Restaurant el = super.get(id);
        super.mark(el);
    }

    @PutMapping(value = "/{id}/unmark")
    public void unMark(@PathVariable("id") int id) {
        Restaurant el = super.get(id);
        super.unMark(el);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

}
