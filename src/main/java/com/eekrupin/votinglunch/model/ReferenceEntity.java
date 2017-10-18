package com.eekrupin.votinglunch.model;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public class ReferenceEntity extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "description", nullable = false)
    @SafeHtml
    protected String description;

    public ReferenceEntity() {
    }

    public ReferenceEntity(Integer id) {
        super(id);
    }

    public ReferenceEntity(Integer id, String description) {
        super(id);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, %s)", getClass().getName(), getId(), getDescription());
    }
}
