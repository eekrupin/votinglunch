package com.eekrupin.votinglunch.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;

public abstract class AbstractReferenceEntity extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "description", nullable = false)
    @SafeHtml
    protected String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, %s)", getClass().getName(), getId(), getId(), getDescription());
    }
}
