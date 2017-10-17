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

    @NotNull
    private Integer price;

    public DishTo() {
    }

    public DishTo(Integer id, Integer restaurant_id, String description, Integer price) {
        this(id, restaurant_id, description, price, false);
    }

    public DishTo(Integer id, Integer restaurant_id, String description, Integer price, boolean deletionMark) {
        super(id);
        this.description = description;
        this.restaurant_id = restaurant_id;
        this.price = price;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + getId() +
                ", description='" + description + '\'' +
                ", restaurant_id='" + restaurant_id + '\'' +
                '}';
    }
}
