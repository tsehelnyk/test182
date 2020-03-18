package com.dev.test182.controller;

import com.dev.test182.model.User;
import com.dev.test182.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private static final String DEFAULT_NAME = "anonymous";

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String greeting(Model model, Authentication authentication) {
        String name = DEFAULT_NAME;
        if (authentication != null) {
            User user = userService.getByLogin(authentication.getName());
            if (user != null) {
                name = user.getName();
            } else authentication.setAuthenticated(false);
        }
        model.addAttribute("name", name);
        return "index";
    }
}
