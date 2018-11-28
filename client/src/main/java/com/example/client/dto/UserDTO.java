package com.example.client.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String confirmedPassword;
}
