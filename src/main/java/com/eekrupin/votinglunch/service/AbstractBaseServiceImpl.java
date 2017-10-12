package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.AbstractBaseEntity;
import com.eekrupin.votinglunch.repository.interfaces.AbstractReferenceRepository;
import com.eekrupin.votinglunch.util.exception.NotFoundException;
import org.springframework.util.Assert;

import java.util.List;

import static com.eekrupin.votinglunch.util.ValidationUtil.checkNotFoundWithId;

public abstract class AbstractBaseServiceImpl<T extends AbstractBaseEntity> implements BaseService<T>{

    private final AbstractReferenceRepository<T> repository;

    public AbstractBaseServiceImpl(AbstractReferenceRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T reference) {
        Assert.notNull(reference, String.format("%s must not be null", reference.getClass().getName()));
        return repository.save(reference);
    }

    @Override
    public T get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(T reference) {
        Assert.notNull(reference, String.format("%s must not be null", reference.getClass().getName()));
        repository.save(reference);
    }

    @Override
    public List<T> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public void mark(T reference){
        reference.setDeletionMark(true);
        update(reference);
    }

    @Override
    public void unMark(T reference){
        reference.setDeletionMark(false);
        update(reference);
    }

}
