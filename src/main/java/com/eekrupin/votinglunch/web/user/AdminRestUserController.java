package com.eekrupin.votinglunch.web.user;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.service.UserService;
import com.eekrupin.votinglunch.web.AbstractBaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdminRestUserController.REST_URL)
public class AdminRestUserController extends AbstractBaseRestController<User> {

    public static final String REST_URL = AbstractBaseRestController.REST_URL + "/users";

    private ExtendUserController extendController;

    @Autowired
    public AdminRestUserController(UserService service) {
        super(service);
        extendController = new ExtendUserController(service);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam("email") String email) {
        return extendController.getByEmail(email);
    }

}
