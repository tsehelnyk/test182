package com.dev.test182.controller;

import com.dev.test182.model.dto.UserRegistrationDto;
import com.dev.test182.service.AuthenticationService;
import com.dev.test182.utils.web.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private WebUtils webUtils;

    @GetMapping("/registration")
    public String registration(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public RedirectView register(@RequestParam(name = "name") String name,
                                 @RequestParam(name = "login") String login,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "repeated_password") String repeatedPassword) {
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setName(name);
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setRepeatedPassword(repeatedPassword);
        String ip = webUtils.getClientIp();
        userDto.setIp(ip);
        authenticationService.register(userDto);
        return new RedirectView("/user/account");
    }
}
