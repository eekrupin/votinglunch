package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.repository.interfaces.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl extends AbstractBaseServiceImpl<Restaurant> implements BaseService<Restaurant> {

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        super(repository);
    }


}
