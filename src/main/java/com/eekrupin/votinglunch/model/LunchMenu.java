package com.eekrupin.votinglunch.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = LunchMenu.ALL, query = "SELECT el FROM LunchMenu el where el.restaurant = :restaurant")
})
@Table(name = "lunchmenus")
public class LunchMenu extends AbstractReferenceEntity{

    public static final String ALL = "LunchMenu.getAll";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public LunchMenu() {
    }

    public LunchMenu(Integer id, Restaurant restaurant, String description) {
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
        return "Restaurant{" +
                "id='" + getId() + '\'' +
                "restaurant='" + (restaurant.isNew() ? "(new)" : restaurant.description) + '\'' +
                "description='" + description + '\'' +
                '}';
    }
}
