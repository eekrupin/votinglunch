package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.AbstractReferenceEntity;
import com.eekrupin.votinglunch.repository.interfaces.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public abstract class DataJpaReferenceRepository <T extends AbstractReferenceEntity> implements ReferenceRepository<T>{

    @Autowired
    private
    CrudReferenceRepository<T> crudReferenceRepository;

    @Override
    @Transactional
    public T save(T reference) {
        return crudReferenceRepository.save(reference);
    }

    @Override
    public boolean delete(int id) {
        return crudReferenceRepository.delete(id)!=0;
    }

    @Override
    public T get(int id) {
        return crudReferenceRepository.getOne(id);
    }

    @Override
    public List<T> getAll() {
        return crudReferenceRepository.getAll();
    }

    @Override
    public void mark(T reference) {
        reference.setDeletionMark(true);
        save(reference);
    }

    @Override
    public void unMark(T reference) {
        reference.setDeletionMark(false);
        save(reference);
    }
}
