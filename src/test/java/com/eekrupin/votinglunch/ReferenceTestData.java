package com.eekrupin.votinglunch;

import com.eekrupin.votinglunch.matcher.BeanMatcher;
import com.eekrupin.votinglunch.model.*;

import java.util.Objects;

import static com.eekrupin.votinglunch.model.AbstractBaseEntity.START_SEQ;

public class ReferenceTestData {
    public static final int RESTAURANT_ID = START_SEQ + 2;

    public static final Restaurant RESTAURANT = new Restaurant(RESTAURANT_ID, "First Restaurant");

    public static final BeanMatcher<ReferenceEntity> MATCHER = BeanMatcher.of(ReferenceEntity.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.isDeletionMark(), actual.isDeletionMark())
                    )
    );

}