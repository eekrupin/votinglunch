package com.eekrupin.votinglunch;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.to.UserTo;
import com.eekrupin.votinglunch.util.UserUtil;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), !user.isDeletionMark(), true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public static Integer safeGetIdOrDefaultId(){
        AuthorizedUser authorizedUser = safeGet();
        if (authorizedUser==null) {
            return getDefaultUserId();
        }
        else {
            return authorizedUser.getId();
        }
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public int getId() {
        return userTo.getId();
    }

    public static int id() {
        return get().userTo.getId();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public static Integer getDefaultUserId() {
        return 100000;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}