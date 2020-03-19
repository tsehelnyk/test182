package com.dev.test182.security;

import com.dev.test182.model.User;
import com.dev.test182.service.UserService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userService.getByLogin(login);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(login);
            builder.password(user.getPassword());
            builder.roles(user.getRoles().stream()
                    .map(r -> r.getRoleName())
                    .collect(Collectors.toList())
                    .toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return builder.build();
    }
}
