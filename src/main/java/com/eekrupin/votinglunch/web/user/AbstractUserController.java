package com.eekrupin.votinglunch.web.user;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.eekrupin.votinglunch.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public User get(int id){
        log.info("get {}", id);
        return service.get(id);
    }

    public User getByEmail(String email){
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
