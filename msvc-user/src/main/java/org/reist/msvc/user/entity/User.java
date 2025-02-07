package org.reist.msvc.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class User  implements Serializable {

    @Serial
    private static final long serialVersionUID = -7939503531335783842L;

    @Id
    private Long id;
    @OneToOne
    @MapsId
    private Username username;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private String address;
    private String photo;
    @Column(unique = true)
    private String identification;

    public User() {
        super();
    }
    public User(Username username) {
        super();
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}