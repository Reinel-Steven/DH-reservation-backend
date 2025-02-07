package org.reist.msvc.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.reist.msvc.user.entity.Role;
import org.reist.msvc.user.entity.SessionLog;
import org.reist.msvc.user.entity.Username;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UsernameDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3561990388845425106L;
    private String nickname;
    @NotEmpty
    @Size.List({
            @Size(min = 5, message = "Contraseña muy corta"),
            @Size(max = 20, message = "Contraseña muy larga")
    })
    private String password;
    private Set<String> role;
    private SessionLog sessionLog;

    public UsernameDto(String nickname, String password, Set<String> role, SessionLog sessionLog) {
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.sessionLog = sessionLog;
    }

}
