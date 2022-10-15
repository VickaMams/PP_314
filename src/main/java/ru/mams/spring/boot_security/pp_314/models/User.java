package ru.mams.spring.boot_security.pp_314.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String firstName;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private String age;
    @Column(name = "secondname")
    private String lastName;


    @Column(name = "email", unique = true, nullable = false)
    private String username;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles = new HashSet<>();

    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    } // срок Действия Учетной Записи
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    } // не Заблокирована Ли Учетная запись
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    } // не Истек Ли Срок Действия Учетных Данных
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setRole(Role role) {
        roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + username + '\'' +
                ", roleList=" + roles +
                '}';
    }
}
