package com.eekrupin.votinglunch.model.data;

import com.eekrupin.votinglunch.model.AbstractBaseEntity;
import com.eekrupin.votinglunch.model.Dish;
import com.eekrupin.votinglunch.model.LunchMenu;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.util.DateUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.Format;
import java.time.LocalDate;

@Entity
@Table(name = "menuconsist")
public class MenuConsist extends AbstractBaseEntity {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private LunchMenu lunchMenu;

    @ManyToOne(fetch = FetchType.LAZY)
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
                "date='" + DateUtil.toString(getDate()) + '\'' +
                "restaurant='" + (getRestaurant().isNew() ? "(new)" : getRestaurant().getDescription()) + '\'' +
                "lunchMenu='" + (getLunchMenu().isNew() ? "(new)" : getLunchMenu().getDescription()) + '\'' +
                "dish='" + (getDish().isNew() ? "(new)" : getDish().getDescription()) + '\'' +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LunchMenu getLunchMenu() {
        return lunchMenu;
    }

    public void setLunchMenu(LunchMenu lunchMenu) {
        this.lunchMenu = lunchMenu;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

}
