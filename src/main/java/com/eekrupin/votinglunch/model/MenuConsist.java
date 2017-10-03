package com.eekrupin.votinglunch.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class MenuConsist extends AbstractBaseEntity{

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private LunchMenu lunchMenu;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Dish dish;

    public MenuConsist() {
    }

    public MenuConsist(Integer id, Restaurant restaurant, LunchMenu lunchMenu, Dish dish) {
        super(id);
        this.restaurant = restaurant;
        this.lunchMenu = lunchMenu;
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "LunchMenu{" +
                "id='" + getId() + '\'' +
                "restaurant='" + (restaurant.isNew() ? "(new)" : restaurant.description) + '\'' +
                "lunchMenu='" + (lunchMenu.isNew() ? "(new)" : lunchMenu.description) + '\'' +
                "dish='" + (dish.isNew() ? "(new)" : dish.description) + '\'' +
                '}';
    }


}
