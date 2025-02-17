package com.reist.reservation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8601375530658119685L;

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

}
