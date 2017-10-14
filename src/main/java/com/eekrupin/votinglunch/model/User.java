package com.eekrupin.votinglunch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
//@NamedQueries({
//        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=:email")
////        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
////        @NamedQuery(name = User.MARK, query = "UPDATE User u SET u.deletionMark = true WHERE u.id=:id"),
////        @NamedQuery(name = User.UNMARK, query = "UPDATE User u SET u.deletionMark = false WHERE u.id=:id"),
////        @NamedQuery(name = User.ALL, query = "SELECT u FROM User u")
//})
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends ReferenceEntity {

//    public static final String BY_EMAIL = "User.getByEmail";

//    public static final String DELETE = "User.delete";
//    public static final String MARK = "User.mark";
//    public static final String UNMARK = "User.unmark";
//    public static final String ALL = "User.getAll";

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @SafeHtml
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 3)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getDescription(), u.getEmail(), u.getPassword(), u.getRoles());
        this.setDeletionMark(u.isDeletionMark());
    }

    public User(Integer id, String description, String email, String password, Role role, Role... roles) {
        this(id, description, email, password, EnumSet.of(role, roles));
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
