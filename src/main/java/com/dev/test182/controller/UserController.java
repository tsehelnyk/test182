package com.dev.test182.controller;

import com.dev.test182.model.User;
import com.dev.test182.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/account")
    public String account(@RequestParam(name = "name", required = false, defaultValue = "World")
                                      String name, Model model) {
        User user = userService.get(1L);
        model.addAttribute("name", user.getLogin());
        model.addAttribute("ip", user.getIp());
        model.addAttribute("timezone_offset", user.getTimeZone());
        return "account";
    }

    @PostMapping("/set-timezone")
    public RedirectView setTimezone(@RequestParam(name = "timezone_offset", required = false, defaultValue = "+00:00")
                                          String timezone, Model model) {
        User user = userService.get(1L);
        user.setTimeZone(timezone);
        userService.save(user);
        return new RedirectView("/account");
    }

    @PostMapping("/complete")
    public String setNewPassword(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "account";
    }

}
