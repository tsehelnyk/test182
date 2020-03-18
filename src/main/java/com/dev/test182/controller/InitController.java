package com.dev.test182.controller;

import com.dev.test182.model.Role;
import com.dev.test182.model.User;
import com.dev.test182.service.RoleService;
import com.dev.test182.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void postConstruct() {
        Role roleAdmin = new Role();
        roleAdmin.setRoleName("ADMIN");
        roleAdmin = roleService.save(roleAdmin);
        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.getRoles().add(roleAdmin);
        userService.save(admin);

        Role roleUser = new Role();
        roleUser.setRoleName("USER");
        roleUser = roleService.save(roleUser);
        User user = new User();
        user.setLogin("user");
        user.setPassword(passwordEncoder.encode("123"));
        user.getRoles().add(roleUser);
        userService.save(user);
    }
}
