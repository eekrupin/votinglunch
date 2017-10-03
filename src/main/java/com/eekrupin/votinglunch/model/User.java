package com.eekrupin.votinglunch.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.MARK, query = "UPDATE User u SET u.deletionMark = true WHERE u.id=:id"),
        @NamedQuery(name = User.UNMARK, query = "UPDATE User u SET u.deletionMark = false WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=:email")
})

@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractReferenceEntity{

    public static final String DELETE = "User.delete";
    public static final String MARK = "User.mark";
    public static final String UNMARK = "User.unmark";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL = "User.getAll";

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @SafeHtml
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 3)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User() {
    }

    public User(Integer id, String description, String email, String password, Collection<Role> roles) {
        super(id, description);
        this.email = email;
        this.password = password;
        setRoles(roles);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", mark='" + isDeletionMark() + '\'' +
                '}';
    }
}
