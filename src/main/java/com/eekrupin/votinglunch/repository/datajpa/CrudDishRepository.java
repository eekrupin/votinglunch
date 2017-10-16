package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends AbstractCrudReferenceRepository<Dish>, JpaRepository<Dish, Integer> {

    List<Dish> getAllByRestaurant_Id(Integer restaurant_id);



}
