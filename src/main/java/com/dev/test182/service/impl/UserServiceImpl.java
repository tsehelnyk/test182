package com.dev.test182.service.impl;

import com.dev.test182.model.User;
import com.dev.test182.repository.UserRepository;
import com.dev.test182.service.AuthenticationService;
import com.dev.test182.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public User changePassword(String login, String oldPassword,
                               String password, String repeatedPassword) {
        if (!password.equals(repeatedPassword)) {
            throw new RuntimeException("Passwords do not match!");
        }
        User user = authenticationService.login(login, oldPassword);
        user.setPassword(passwordEncoder.encode(password));
        return save(user);
    }


}
