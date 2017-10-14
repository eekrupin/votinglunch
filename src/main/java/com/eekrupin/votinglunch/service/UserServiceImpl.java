package com.eekrupin.votinglunch.service;

import com.eekrupin.votinglunch.AuthorizedUser;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.repository.interfaces.UserRepository;
import com.eekrupin.votinglunch.util.ValidationUtil;
import com.eekrupin.votinglunch.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("userService")
public class UserServiceImpl extends AbstractBaseServiceImpl<User> implements UserService, UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return ValidationUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

}
