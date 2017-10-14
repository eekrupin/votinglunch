package com.eekrupin.votinglunch.web.user;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.service.UserService;
import com.eekrupin.votinglunch.web.AbstractBaseController;

public class AbstractUserController extends AbstractBaseController<User> {

    private UserService service;

    public AbstractUserController(UserService service) {
        super(service);
        this.service = service;
    }

    public User getByEmail(String email){
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
