package com.eekrupin.votinglunch.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
//@NamedQueries({
//        @NamedQuery(name = Dish.ALL, query = "SELECT el FROM Dish el where el.lunchMenu = :lunchMenu")
//})
@Table(name = "dishes")
public class Dish extends ReferenceEntity {

    public static final String ALL = "Dish.getAll";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Integer id, Restaurant restaurant, String description) {
        super(id, description);
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + getId() + '\'' +
                "restaurant='" + (restaurant.isNew() ? "(new)" : restaurant.description) + '\'' +
                "description='" + description + '\'' +
                '}';
    }
}
