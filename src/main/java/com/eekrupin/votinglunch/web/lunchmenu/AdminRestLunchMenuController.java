package com.eekrupin.votinglunch.web.lunchmenu;

import com.eekrupin.votinglunch.View;
import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.service.LunchMenuService;
import com.eekrupin.votinglunch.to.LunchMenuTo;
import com.eekrupin.votinglunch.util.LunchMenuUtil;
import com.eekrupin.votinglunch.web.AbstractBaseController;
import com.eekrupin.votinglunch.web.AbstractBaseRestController;
import org.hibernate.mapping.Collection;
import org.jsoup.select.Collector;
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
@RequestMapping(AdminRestLunchMenuController.REST_URL)
public class AdminRestLunchMenuController extends AbstractBaseController<LunchMenu> {
    public static final String REST_URL = "/rest/admin" + "/lunchmenu";

    private ExtendLunchMenuController extendController;

    @Autowired
    private LunchMenuUtil lunchMenuUtil;

    @Autowired
    public AdminRestLunchMenuController(LunchMenuService service) {
        super(service);
        extendController = new ExtendLunchMenuController(service);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LunchMenu> getAllByRestaurant(@RequestParam("restaurant") Integer restaurant_id) {
        return extendController.getAllByRestaurant(restaurant_id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LunchMenuTo> createWithLocation(@Validated(View.ValidatedRestUI.class) @RequestBody LunchMenuTo el) {
        LunchMenu newLunchMenu = lunchMenuUtil.createNewFromTo(el);
        LunchMenu created = super.create(newLunchMenu);
        LunchMenuTo createdTo = lunchMenuUtil.asTo(created);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdTo.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(createdTo);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.ValidatedRestUI.class) @RequestBody LunchMenuTo el, @PathVariable("id") int id) {
        super.update(lunchMenuUtil.asLunchMenu(el), id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LunchMenuTo getTo(@PathVariable("id") int id) {
        return lunchMenuUtil.asTo(super.get(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LunchMenuTo> getAllTo() {
        List<LunchMenuTo> collect = super.getAll().stream().map(el -> lunchMenuUtil.asTo(el)).collect(Collectors.toList());
        return collect;
    }


    @PutMapping(value = "/{id}/mark")
    public void mark(@PathVariable("id") int id) {
        LunchMenu el = super.get(id);
        super.mark(el);
    }

    @PutMapping(value = "/{id}/unmark")
    public void unMark(@PathVariable("id") int id) {
        LunchMenu el = super.get(id);
        super.unMark(el);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

}
