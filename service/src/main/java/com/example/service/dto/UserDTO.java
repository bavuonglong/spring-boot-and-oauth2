package com.example.service.dto;

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

    public com.example.service.dao.User toUserDAO() {
        return com.example.service.dao.User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
