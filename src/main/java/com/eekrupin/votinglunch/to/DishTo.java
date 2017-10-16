package com.eekrupin.votinglunch.to;

import com.eekrupin.votinglunch.View;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DishTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @SafeHtml(groups = {View.ValidatedRestUI.class})
    private String description;

    @NotNull
    private Integer restaurant_id;

    public DishTo() {
    }

    public DishTo(Integer id, Integer restaurant_id, String description) {
        this(id, restaurant_id, description, false);
    }

    public DishTo(Integer id, Integer restaurant_id, String description, boolean deletionMark) {
        super(id);
        this.description = description;
        this.restaurant_id = restaurant_id;
        this.setDeletionMark(deletionMark);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + getId() +
                ", description='" + description + '\'' +
                ", restaurant_id='" + restaurant_id + '\'' +
                '}';
    }
}
