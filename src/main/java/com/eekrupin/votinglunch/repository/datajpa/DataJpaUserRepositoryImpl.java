package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl extends DataJpaReferenceRepository<User> implements UserRepository{

    private CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaUserRepositoryImpl(CrudUserRepository crudUserRepository) {
        super(crudUserRepository);
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

//    CrudUserRepository getCrudUserRepository() {
//        return crudUserRepository;
//    }
//
//    @Autowired
//    public void setCrudUserRepository(CrudUserRepository crudUserRepository) {
//        this.crudUserRepository = crudUserRepository;
//    }


}
