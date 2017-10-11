package com.eekrupin.votinglunch.model;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;

@MappedSuperclass
//@NamedQueries({
//        @NamedQuery(name = AbstractReferenceEntity.DELETE, query = "DELETE FROM AbstractReferenceEntity el WHERE el.id=:id"),
//        @NamedQuery(name = AbstractReferenceEntity.MARK, query = "UPDATE AbstractReferenceEntity el SET el.deletionMark = true WHERE el.id=:id"),
//        @NamedQuery(name = AbstractReferenceEntity.UNMARK, query = "UPDATE AbstractReferenceEntity el SET el.deletionMark = false WHERE el.id=:id"),
//        @NamedQuery(name = AbstractReferenceEntity.ALL, query = "SELECT el FROM AbstractReferenceEntity el")
//})
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.FIELD)
public abstract class AbstractReferenceEntity extends AbstractBaseEntity {

//    public static final String DELETE = "Ref.delete";
//    public static final String MARK = "Ref.mark";
//    public static final String UNMARK = "Ref.unmark";
//    public static final String ALL = "Ref.getAll";

    @NotBlank
    @Column(name = "description", nullable = false)
    @SafeHtml
    protected String description;

    public AbstractReferenceEntity() {
    }

    public AbstractReferenceEntity(Integer id) {
        super(id);
    }

    public AbstractReferenceEntity(Integer id, String description) {
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
        return String.format("Entity %s (%s, %s)", getClass().getName(), getId(), getId(), getDescription());
    }
}
