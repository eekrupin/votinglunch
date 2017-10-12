package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.util.exception.NotFoundException;

public interface UserService extends BaseService<User> {

    User getByEmail(String email) throws NotFoundException;

}
