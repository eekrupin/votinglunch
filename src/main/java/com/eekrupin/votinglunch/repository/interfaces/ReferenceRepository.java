package com.eekrupin.votinglunch.repository.interfaces;

import java.util.List;

//only for inherit
public abstract interface ReferenceRepository <T>{
    T save(T reference);

    void update(T reference);

    boolean delete(int id);

    T get(int id);

    List<T> getAll();

    void mark(T reference);

    void unMark(T reference);
}
