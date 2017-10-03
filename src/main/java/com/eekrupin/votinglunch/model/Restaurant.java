package com.eekrupin.votinglunch.model;

import javax.persistence.*;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "description", name = "restaurant_unique_description_idx")})
public class Restaurant extends AbstractReferenceEntity{

    public Restaurant() {
    }

    public Restaurant(Integer id, String description) {
        super(id, description);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + getId() + '\'' +
                "description='" + description + '\'' +
                '}';
    }
}
