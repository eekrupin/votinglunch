package com.eekrupin.votinglunch.repository.datajpa;

import com.eekrupin.votinglunch.model.AbstractReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudReferenceRepository <T extends AbstractReferenceEntity> extends JpaRepository<T, Integer>{

    @Modifying
    @Transactional
    @Query("DELETE FROM AbstractReferenceEntity el WHERE el.id=:id")
    int delete(@Param("id") int id);

    T save(T reference);

    T getById(int id);

    List<T> getAll();

}
