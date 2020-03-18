package com.dev.test182.controller;

import com.dev.test182.model.User;
import com.dev.test182.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/account")
    public String account(Authentication authentication,
                          Model model) {
        User user = userService.getByLogin(authentication.getName());
        model.addAttribute("name", user.getLogin());
        model.addAttribute("ip", user.getIp());
        model.addAttribute("timezone_offset", user.getTimeZone());
        return "account";
    }

    @PostMapping("/set-timezone")
    public RedirectView setTimezone(Authentication authentication,
            @RequestParam(name = "timezone_offset", required = false, defaultValue = "+00:00") String timezone,
                                    RedirectAttributes attributes) {
        User user = userService.getByLogin(authentication.getName());
        user.setTimeZone(timezone);
        userService.save(user);
        return new RedirectView("/user/account");
    }

    @PostMapping("/complete")
    public String setNewPassword(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "account";
    }

}
