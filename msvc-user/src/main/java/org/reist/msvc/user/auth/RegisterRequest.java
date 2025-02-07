package org.reist.msvc.user.auth;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reist.msvc.user.entity.SessionLog;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotEmpty
    private String nickname;
    @NotEmpty
    @Size.List({
            @Size(min = 5, message = "Contraseña muy corta"),
            @Size(max = 20, message = "Contraseña muy larga")
    })
    private String password;
    private SessionLog sessionLog;
}
