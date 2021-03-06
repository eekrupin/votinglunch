package com.eekrupin.votinglunch.util;


import com.eekrupin.votinglunch.AuthorizedUser;
import com.eekrupin.votinglunch.model.Role;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.util.exception.ForbiddenException;
import com.eekrupin.votinglunch.util.exception.NotFoundException;
import com.eekrupin.votinglunch.HasId;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String arg) {
        if (!found) {
            throw new NotFoundException(arg);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static void CheckUserOnAuthority(User user) {
        AuthorizedUser authorizedUser = AuthorizedUser.safeGet();
        Boolean userFitsTheConditions = authorizedUser!=null && !authorizedUser.getAuthorities().contains(Role.ROLE_ADMIN) && !user.getId().equals(AuthorizedUser.id());
        if ( userFitsTheConditions ){
            throw new ForbiddenException("Wrong user: " + user.getId());
        }
    }

}