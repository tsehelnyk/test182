package com.dev.test182.service;

import com.dev.test182.model.User;
import com.dev.test182.model.dto.UserRegistrationDto;

public interface AuthenticationService {
    User login(String login, String password);

    User register(UserRegistrationDto userDto);
}
