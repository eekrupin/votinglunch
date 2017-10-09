package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaUserRepositoryImpl extends DataJpaReferenceRepository<User> implements UserRepository{

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    CrudUserRepository getCrudUserRepository() {
        return crudUserRepository;
    }

    public void setCrudUserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }
}
