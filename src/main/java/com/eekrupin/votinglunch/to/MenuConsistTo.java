package com.eekrupin.votinglunch.to;

import com.eekrupin.votinglunch.View;
import com.eekrupin.votinglunch.util.DateUtil;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class MenuConsistTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private LocalDate date;

    @NotNull
    private Integer restaurant_id;

    @NotNull
    private Integer lunchMenu_id;

    @NotNull
    private Integer dish_id;

    public MenuConsistTo() {
    }

    public MenuConsistTo(Integer id, LocalDate date, Integer restaurant_id, Integer lunchMenu_id, Integer dish_id) {
        super(id);
        this.date = date;
        this.restaurant_id = restaurant_id;
        this.lunchMenu_id = lunchMenu_id;
        this.dish_id = dish_id;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Integer getLunchMenu_id() {
        return lunchMenu_id;
    }

    public void setLunchMenu_id(Integer lunchMenu_id) {
        this.lunchMenu_id = lunchMenu_id;
    }

    public Integer getDish_id() {
        return dish_id;
    }

    public void setDish_id(Integer dish_id) {
        this.dish_id = dish_id;
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
                ", lunchMenu_id='" + lunchMenu_id + '\'' +
                ", dish_id='" + dish_id + '\'' +
                '}';
    }
}
