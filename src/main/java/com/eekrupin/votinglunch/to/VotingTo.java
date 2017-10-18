package com.eekrupin.votinglunch.to;

import com.eekrupin.votinglunch.util.DateUtil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class VotingTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer restaurant_id;

    public VotingTo() {
    }

    public VotingTo(Integer id, LocalDate date, Integer restaurant_id) {
        super(id);
        this.date = date;
        this.restaurant_id = restaurant_id;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MenuConsistTo{" +
                "id=" + getId() +
                ", date='" + DateUtil.toString(getDate()) + '\'' +
                ", restaurant_id='" + restaurant_id + '\'' +
                '}';
    }
}
