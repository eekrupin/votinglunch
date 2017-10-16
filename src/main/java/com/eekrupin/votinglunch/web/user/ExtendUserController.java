package com.eekrupin.votinglunch.web.user;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ExtendUserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private UserService service;

    ExtendUserController(UserService service) {
        this.service = service;
    }

    User getByEmail(String email){
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
