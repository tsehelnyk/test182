package com.dev.test182.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthenticationController {
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestBody @Valid UserRegistrationDto userDto)
//            throws AuthenticationException {
//        User user = new User();
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        authenticationService.login(userDto.getEmail(), userDto.getPassword());
//        return "user logined";
//    }

    @GetMapping("/register")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "registration";
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String register(@RequestBody @Valid UserRegistrationDto userDto) {
//        authenticationService.register(userDto.getName(), userDto.getEmail(),
//                userDto.getPassword());
//        return "user registered";
//    }
}


