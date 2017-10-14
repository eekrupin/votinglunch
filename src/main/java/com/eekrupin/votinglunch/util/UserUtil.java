package com.eekrupin.votinglunch.util;

import com.eekrupin.votinglunch.model.Role;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.to.UserTo;

public class UserUtil {

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getDescription(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setDescription(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
