package com.eekrupin.votinglunch;

import com.eekrupin.votinglunch.matcher.BeanMatcher;
import com.eekrupin.votinglunch.model.*;
import com.eekrupin.votinglunch.to.DishTo;
import com.eekrupin.votinglunch.to.LunchMenuTo;
import com.eekrupin.votinglunch.to.MenuConsistTo;

import java.time.Month;
import java.util.Objects;

import static com.eekrupin.votinglunch.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDate.of;

public class ReferenceTestData {
    private static int count = 2;

    public static final int RESTAURANT_ID = START_SEQ + count++;
    public static final int RESTAURANT_ID2 = START_SEQ + count++;

    public static final int LUNCH_MENU_ID1 = START_SEQ + count++;
    public static final int LUNCH_MENU_ID2 = START_SEQ + count++;

    public static final int DISH1_R1_ID = START_SEQ + count++;
    public static final int DISH2_R1_ID = START_SEQ + count++;
    public static final int DISH1_R2_ID = START_SEQ + count++;

    public static final int MENUCONSIST_D1_R1_ID1 = START_SEQ + count++;
    public static final int MENUCONSIST_D1_R1_ID2 = START_SEQ + count++;
    public static final int MENUCONSIST_D1_R2_ID3 = START_SEQ + count++;
    public static final int MENUCONSIST_D2_R1_ID4 = START_SEQ + count++;


    public static final Restaurant RESTAURANT = new Restaurant(RESTAURANT_ID, "First Restaurant");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID2, "Second Restaurant");

    public static final LunchMenuTo LUNCH_MENU1 = new LunchMenuTo(LUNCH_MENU_ID1, RESTAURANT_ID, "Menu of First restaurant", false);
    public static final LunchMenuTo LUNCH_MENU2 = new LunchMenuTo(LUNCH_MENU_ID2, RESTAURANT_ID2, "Menu of Second restaurant", false);

    public static final DishTo DISH1_R1 = new DishTo(DISH1_R1_ID, RESTAURANT_ID, "Dish 1 of First restaurant", false);
    public static final DishTo DISH2_R1 = new DishTo(DISH2_R1_ID, RESTAURANT_ID, "Dish 2 of First restaurant", false);
    public static final DishTo DISH1_R2 = new DishTo(DISH1_R2_ID, RESTAURANT_ID2, "Dish 2 of First restaurant", false);


    public static final MenuConsistTo MENUCONSIST_D1_R1_1 = new MenuConsistTo(MENUCONSIST_D1_R1_ID1, of(2017, Month.OCTOBER, 16), RESTAURANT_ID, LUNCH_MENU_ID1, DISH1_R1_ID);
    public static final MenuConsistTo MENUCONSIST_D1_R1_2 = new MenuConsistTo(MENUCONSIST_D1_R1_ID2, of(2017, Month.OCTOBER, 16), RESTAURANT_ID, LUNCH_MENU_ID1, DISH2_R1_ID);
    public static final MenuConsistTo MENUCONSIST_D1_R2_3 = new MenuConsistTo(MENUCONSIST_D1_R2_ID3, of(2017, Month.OCTOBER, 16), RESTAURANT_ID2, LUNCH_MENU_ID2, DISH1_R2_ID);
    public static final MenuConsistTo MENUCONSIST_D2_R1_4 = new MenuConsistTo(MENUCONSIST_D2_R1_ID4, of(2017, Month.OCTOBER, 17), RESTAURANT_ID, LUNCH_MENU_ID1, DISH1_R1_ID);

    public static final BeanMatcher<ReferenceEntity> MATCHER = BeanMatcher.of(ReferenceEntity.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.isDeletionMark(), actual.isDeletionMark())
                    )
    );

    public static final BeanMatcher<LunchMenuTo> MATCHER_MENU = BeanMatcher.of(LunchMenuTo.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.isDeletionMark(), actual.isDeletionMark())
                            && Objects.equals(expected.getRestaurant_id(), actual.getRestaurant_id())
                    )
    );

    public static final BeanMatcher<DishTo> MATCHER_DISH = BeanMatcher.of(DishTo.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.isDeletionMark(), actual.isDeletionMark())
                            && Objects.equals(expected.getRestaurant_id(), actual.getRestaurant_id())
                    )
    );

    public static final BeanMatcher<MenuConsistTo> MATCHER_MENUCONSIST = BeanMatcher.of(MenuConsistTo.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDate(), actual.getDate())
                            && Objects.equals(expected.getRestaurant_id(), actual.getRestaurant_id())
                            && Objects.equals(expected.getLunchMenu_id(), actual.getLunchMenu_id())
                            && Objects.equals(expected.getDish_id(), actual.getDish_id())
                    )
    );

}