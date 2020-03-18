package com.dev.test182.model.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String login;
    private String password;
    private String repeatedPassword;
    private String ip;
}
