package com.eekrupin.votinglunch.model.data;

import com.eekrupin.votinglunch.model.AbstractBaseEntity;
import com.eekrupin.votinglunch.model.Restaurant;
import com.eekrupin.votinglunch.model.User;
import com.eekrupin.votinglunch.util.DateUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "voting")
public class Voting extends AbstractBaseEntity {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public Voting() {
    }

    public Voting(Integer id, LocalDate date, User user, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Voting{" +
                "id='" + getId() + '\'' +
                ", date='" + DateUtil.toString(getDate()) + '\'' +
                ", restaurant='" + (getRestaurant().isNew() ? "(new)" : getRestaurant().getDescription()) + '\'' +
                ", user='" + (getUser().isNew() ? "(new)" : getUser().getDescription()) + '\'' +
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
