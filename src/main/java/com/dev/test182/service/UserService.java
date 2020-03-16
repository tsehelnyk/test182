package com.dev.test182.service;

import com.dev.test182.model.User;

public interface UserService {

    User save(User user);

    User get(Long id);

    User getByLogin(String login);

}
