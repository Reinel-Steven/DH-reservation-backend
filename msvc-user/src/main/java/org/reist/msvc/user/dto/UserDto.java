package org.reist.msvc.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.reist.msvc.user.entity.User;

import java.io.Serial;
import java.io.Serializable;

@Data
@Getter
@Setter
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3561990368845425106L;

    private Long id;
    @NotEmpty
    private String username;
    private String name;
    private String lastName;
    @Email
    @NotEmpty
    private String email;
    private String phone;
    private String address;
    private String photo;
    private String identification;

    public UserDto() {
        super();
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername().getNickname();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.photo = user.getPhoto();
        this.identification = user.getIdentification();
    }

    public boolean isEmpty(){
        return this.username == null ||
                this.name == null ||
                this.email == null;
    }
}
