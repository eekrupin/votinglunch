package com.eekrupin.votinglunch.repository.interfaces;

import com.eekrupin.votinglunch.model.User;

public interface UserRepository extends AbstractReferenceRepository<User> {
    User getByEmail(String email);
}
