package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.repository.interfaces.LunchMenuRepository;
import com.eekrupin.votinglunch.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class LunchMenuServiceImpl extends AbstractBaseServiceImpl<LunchMenu> implements LunchMenuService {

    private final LunchMenuRepository repository;

    @Autowired
    public LunchMenuServiceImpl(LunchMenuRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Transactional
    public LunchMenu get(int id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    public List<LunchMenu> getAllByRestaurant(Integer restaurant_id) {
        Assert.notNull(restaurant_id, "restaurant_id must not be null");
        return repository.getAllByRestaurant(restaurant_id);
    }

}
