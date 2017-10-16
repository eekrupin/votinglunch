package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.LunchMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudLunchMenuRepository extends AbstractCrudReferenceRepository<LunchMenu>, JpaRepository<LunchMenu, Integer> {

    List<LunchMenu> getAllByRestaurant_Id(Integer restaurant_id);



}
