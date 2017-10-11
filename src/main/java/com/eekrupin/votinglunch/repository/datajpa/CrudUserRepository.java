package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudUserRepository extends CrudReferenceRepository<User>, JpaRepository<User, Integer> {

    User getByEmail(String email);
    
}
