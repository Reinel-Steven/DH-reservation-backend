package org.reist.msvc.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="roles", uniqueConstraints = {@UniqueConstraint(columnNames= {"name_role"})})
public class Role  implements Serializable {

    @Serial
    private static final long serialVersionUID = -3561990368845425106L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_role")
    private String nameRole;

    @ManyToMany( mappedBy = "roles")
    private Set<Username> usernames;

    public Role() {
        super();
        this.usernames = new HashSet<>();
    }

    public Role(String nameRole) {
        this.id = 1;
        this.nameRole = nameRole;
    }
}
