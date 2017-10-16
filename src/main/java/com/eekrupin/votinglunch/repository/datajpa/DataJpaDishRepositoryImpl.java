package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.Dish;
import com.eekrupin.votinglunch.repository.interfaces.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl extends AbstractDataJpaReferenceRepository<Dish> implements DishRepository {

    private CrudDishRepository crudRepository;

    @Autowired
    public DataJpaDishRepositoryImpl(CrudDishRepository crudRepository) {
        super(crudRepository);
        this.crudRepository = crudRepository;
    }

    @Override
    public List<Dish> getAllByRestaurant(Integer restaurant_id) {
        return crudRepository.getAllByRestaurant_Id(restaurant_id);
    }

}
