package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends AbstractCrudReferenceRepository<Restaurant>, JpaRepository<Restaurant, Integer> {

}
