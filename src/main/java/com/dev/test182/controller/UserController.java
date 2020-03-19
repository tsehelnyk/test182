package com.dev.test182.controller;

import com.dev.test182.model.User;
import com.dev.test182.service.UserService;
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
                          @RequestParam(name = "msg", required = false,
                                  defaultValue = "Change password") String message, Model model) {
        User user = userService.getByLogin(authentication.getName());
        if (user == null) {
            authentication.setAuthenticated(false);
            return "index";
        }
        model.addAttribute("msg", message);
        model.addAttribute("name", user.getName());
        model.addAttribute("login", user.getLogin());
        model.addAttribute("ip", user.getIp() != null ? user.getIp() : "0.0.0.0");
        model.addAttribute("timezone_offset",
                user.getTimeZone() != null ? user.getTimeZone() : "00:00");
        return "account";
    }

    @PostMapping("/set-timezone")
    public RedirectView setTimezone(Authentication authentication,
                                    @RequestParam(name = "timezone_offset", required = false,
                                            defaultValue = "+00:00") String timezone) {
        User user = userService.getByLogin(authentication.getName());
        user.setTimeZone(timezone);
        userService.save(user);
        return new RedirectView("/user/account");
    }

    @PostMapping("/change-password")
    public RedirectView setNewPassword(Authentication authentication,
                                       RedirectAttributes attributes,
                                       @RequestParam(name = "old_password") String oldPassword,
                                       @RequestParam(name = "password") String password,
                                       @RequestParam(name = "repeated_password")
                                                   String repeatedPassword) {
        User user = userService.changePassword(authentication.getName(),
                oldPassword, password, repeatedPassword);
        attributes.addAttribute("msg", "Password was changed!");
        return new RedirectView("/user/account");
    }

}
