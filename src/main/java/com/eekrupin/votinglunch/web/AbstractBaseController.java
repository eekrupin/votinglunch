package com.eekrupin.votinglunch.web;

import com.eekrupin.votinglunch.model.AbstractBaseEntity;
import com.eekrupin.votinglunch.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.eekrupin.votinglunch.util.ValidationUtil.assureIdConsistent;
import static com.eekrupin.votinglunch.util.ValidationUtil.checkNew;

public abstract class AbstractBaseController<T extends AbstractBaseEntity> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private BaseService<T> service;

    protected AbstractBaseController(BaseService<T> service) {
         this.service = service;
    }

    public T create(T reference) {
        log.info("create {}", reference);
        checkNew(reference);
        return service.create(reference);
    }

    public T get(int id){
        log.info("get {}", id);
        return service.get(id);
    }

    public void update(T reference, int id) {
        log.info("update {} with id={}", reference, id);
        assureIdConsistent(reference, id);
//        checkModificationAllowed(id);
        service.update(reference);
    }

    public List<T> getAll(){
        log.info("getAll");
        return service.getAll();
    }

    public void delete(int id){
        log.info("delete {}", id);
        service.delete(id);
    }

    public void mark(T reference) {
        log.info("mark {} with id={}", reference, reference.getId());
        service.mark(reference);
    }

    public void unMark(T reference) {
        log.info("unMark {} with id={}", reference, reference.getId());
        service.unMark(reference);
    }

//    private void checkModificationAllowed(int id) {
//        if (modificationRestriction && id < AbstractBaseEntity.START_SEQ + 2) {
//            throw new ApplicationException(EXCEPTION_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
//        }
//    }

}
