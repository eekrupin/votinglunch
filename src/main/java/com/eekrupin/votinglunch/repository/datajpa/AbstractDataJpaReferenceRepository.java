package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.ReferenceEntity;
import com.eekrupin.votinglunch.repository.interfaces.AbstractReferenceRepository;
import com.eekrupin.votinglunch.util.exception.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.eekrupin.votinglunch.util.ValidationUtil.checkNotFoundWithId;

public abstract class AbstractDataJpaReferenceRepository<T extends ReferenceEntity> implements AbstractReferenceRepository<T> {

    private static final Sort SORT_ID = new Sort("id");

    private AbstractCrudReferenceRepository<T> crudReferenceRepository;

    public AbstractDataJpaReferenceRepository(AbstractCrudReferenceRepository<T> crudReferenceRepository) {
        this.crudReferenceRepository = crudReferenceRepository;
    }

    AbstractCrudReferenceRepository<T> getCrudReferenceRepository() {
        return crudReferenceRepository;
    }

    @Override
    @Transactional
    public T save(T reference) {
        return crudReferenceRepository.save(reference);
    }

    @Override
    public boolean delete(int id) {
        return crudReferenceRepository.deleteById(id)!=0;
    }

    @Override
    public T get(int id) throws NotFoundException {
        return checkNotFoundWithId(crudReferenceRepository.getById(id), id);
    }

    @Override
    public List<T> getAll() {
        return crudReferenceRepository.findAll(SORT_ID);
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
