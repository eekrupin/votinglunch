package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.util.exception.NotFoundException;

public interface ReferenceService<T> {
    T create(T ref);
    T get(int id) throws NotFoundException;
}
