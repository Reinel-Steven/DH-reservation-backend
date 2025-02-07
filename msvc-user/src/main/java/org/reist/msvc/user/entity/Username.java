package org.reist.msvc.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Username implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = -3229262282256779172L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickname;

    @OneToOne(mappedBy = "username")
    private User user;

    private boolean enable;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usernames_roles",
            joinColumns = @JoinColumn(name="username_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"),
            uniqueConstraints = { @UniqueConstraint(columnNames = { "username_id", "role_id" }) }
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "username", cascade = CascadeType.PERSIST)
    private Set<SessionLog> sessionLogs;

    /* ************************* Constructor ************************** */
    public Username(){
        super();
        this.roles = new HashSet<>();
        this.sessionLogs = new HashSet<>();
    }
    public Username(String nickname, String password, SessionLog sessionLogs) {
        this.nickname = nickname;
        this.enable = true;
        this.password = password;
        this.roles = new HashSet<>();
        this.roles.add(new Role("GENERIC"));
        this.sessionLogs = new HashSet<>();
        this.sessionLogs.add(sessionLogs);
    }

    /* ************************* UserDetails ************************** */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getNameRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username = (Username) o;
        return id != null && id.equals(username.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
