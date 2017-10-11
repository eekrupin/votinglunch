package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.util.exception.NotFoundException;

import java.util.List;

public interface UserService extends ReferenceService<User> {

//    void delete(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

//    void update(User user);
//
//    void mark(User user);
//
//    void unMark(User user);
//
//     List<User> getAll();

}
