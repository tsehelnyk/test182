package com.dev.test182.controller;

import com.dev.test182.model.User;
import com.dev.test182.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                           Model model, RedirectAttributes attributes ) {
        model.addAttribute("name", name);
        User user = new User();
        user.setLogin("Bob");
        user.setPassword("123");
        user.setTimeZone("00:00");
        user.setIp("10.0.0.0");
        user = userService.save(user);
        attributes.addAttribute("id", user.getId());
//        return new RedirectView("/account");
        return "index";
    }

}

