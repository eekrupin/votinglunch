package com.eekrupin.votinglunch;

import com.eekrupin.votinglunch.model.Role;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.web.user.AdminRestController;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try(GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()){
            appCtx.load("spring/spring-app.xml", "spring/mock.xml", "spring/spring-db.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
            User created = adminRestController.create(new User(null, "test01", "email@mail.com", "pass", Role.ROLE_ADMIN));
            System.out.println("created:" + created.toString());
        }
    }
}
