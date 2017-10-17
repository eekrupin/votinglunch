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

    private Integer price;

    @NotNull
    public Dish() {
    }

    public Dish(Integer id, Restaurant restaurant, String description, Integer price) {
        super(id, description);
        this.restaurant = restaurant;
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + getId() + '\'' +
                ", restaurant='" + (restaurant.isNew() ? "(new)" : restaurant.getDescription()) + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price='" + getPrice() + '\'' +
                '}';
    }
}
