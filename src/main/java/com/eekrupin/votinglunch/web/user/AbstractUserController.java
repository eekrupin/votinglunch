package com.eekrupin.votinglunch.web.user;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.service.BaseService;
import com.eekrupin.votinglunch.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.eekrupin.votinglunch.util.ValidationUtil.checkNew;

public class AbstractUserController extends AbstractBaseController<User>{

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
