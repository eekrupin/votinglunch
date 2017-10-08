package com.eekrupin.votinglunch;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try(GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()){
            appCtx.load("spring/spring-app.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));



        }
    }
}
