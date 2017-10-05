package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.User;

public interface UserRepository extends ReferenceRepository<User>{
    User getByEmail(String email);
}
