package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.repository.interfaces.LunchMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaLunchMenuRepositoryImpl extends AbstractDataJpaReferenceRepository<LunchMenu> implements LunchMenuRepository{

    private CrudLunchMenuRepository crudRepository;

    @Autowired
    public DataJpaLunchMenuRepositoryImpl(CrudLunchMenuRepository crudRepository) {
        super(crudRepository);
        this.crudRepository = crudRepository;
    }

    public List<LunchMenu> getAllByRestaurant(Integer restaurant_id) {
        return crudRepository.getAllByRestaurant_Id(restaurant_id);
    }
}
