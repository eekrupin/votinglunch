package com.eekrupin.votinglunch.web.user;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AdminRestController extends AbstractUserController{
    public static final String REST_URL = "/rest/admin/users";

    @Autowired
    public AdminRestController(UserService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user){
        User created = super.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user, @PathVariable("id") int id) {
        super.update(user, id);
    }

    @PutMapping(value = "/{id}/mark")
    public void mark(@PathVariable("id") int id) {
        User user = super.get(id);
        super.mark(user);
    }

    @PutMapping(value = "/{id}/unmark")
    public void unMark(@PathVariable("id") int id) {
        User user = super.get(id);
        super.unMark(user);
    }

    @Override
    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam("email") String email) {
        return super.getByEmail(email);
    }

}
