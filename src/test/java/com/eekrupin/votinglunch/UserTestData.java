package com.eekrupin.votinglunch;

import com.eekrupin.votinglunch.matcher.BeanMatcher;
import com.eekrupin.votinglunch.model.Role;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.web.json.JsonUtil;

import java.util.Objects;

import static com.eekrupin.votinglunch.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final int USER2_ID = 100016;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static final User USER2 = new User(USER2_ID, "User 2", "user2@yandex.ru", "password", Role.ROLE_USER);

    public static final BeanMatcher<User> MATCHER = BeanMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.isDeletionMark(), actual.isDeletionMark())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeWithExtraProps(user, "password", passw);
    }
}