package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.util.exception.NotFoundException;

import java.util.List;

public interface BaseService<T> {

    T create(T ref);

    void update(T ref);

    T get(int id) throws NotFoundException;

    List<T> getAll();

    void delete(int id) throws NotFoundException;

    void mark(T reference);

    void unMark(T reference);

}
