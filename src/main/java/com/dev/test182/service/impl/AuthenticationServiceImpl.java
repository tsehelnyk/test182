package com.dev.test182.service.impl;

import com.dev.test182.model.Role;
import com.dev.test182.model.User;
import com.dev.test182.model.dto.UserRegistrationDto;
import com.dev.test182.repository.RoleRepository;
import com.dev.test182.service.AuthenticationService;
import com.dev.test182.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(String login, String password) {
        User user = userService.getByLogin(login);
        if (user == null
                || ! passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong login or password");
        }
        return user;
    }

    @Override
    public User register(UserRegistrationDto userDto) {
        if (userService.getByLogin(userDto.getLogin()) != null) {
            throw new RuntimeException("This login already exist!");
        }
        if (!userDto.getPassword().equals(userDto.getRepeatedPassword())) {
            throw new RuntimeException("Passwords do not match!");
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setIp(userDto.getIp());
        Role roleUser = roleRepository.getRoleByRoleName("USER");
        user.getRoles().add(roleUser);
        return userService.save(user);
    }
}
