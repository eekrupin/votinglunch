package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.Dish;
import com.eekrupin.votinglunch.repository.interfaces.DishRepository;
import com.eekrupin.votinglunch.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class DishServiceImpl extends AbstractBaseServiceImpl<Dish> implements DishService{

    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Transactional
    public Dish get(int id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    public List<Dish> getAllByRestaurant(Integer restaurant_id) {
        Assert.notNull(restaurant_id, "restaurant_id must not be null");
        return repository.getAllByRestaurant(restaurant_id);
    }

}
