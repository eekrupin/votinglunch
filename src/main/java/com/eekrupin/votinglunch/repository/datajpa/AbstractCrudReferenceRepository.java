package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.ReferenceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract interface AbstractCrudReferenceRepository<T extends ReferenceEntity>{

    @Modifying
    @Transactional
    //made this only for try out use dynamic tables in datajpa
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query.spel-expressions
    @Query("DELETE FROM #{#entityName} el WHERE el.id=:id")
    int deleteById(@Param("id") int id);

    @Transactional
    <S extends T> S save(S reference);

    T getById(int id);

    List<T> findAll(Sort sort);

}
