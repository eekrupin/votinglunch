package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.repository.interfaces.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaRestaurantRepositoryImpl extends AbstractDataJpaReferenceRepository<Restaurant> implements RestaurantRepository{

    @Autowired
    public DataJpaRestaurantRepositoryImpl(CrudRestaurantRepository crudRepository) {
        super(crudRepository);
    }

}
