package com.example.client.controller;

import com.example.client.dto.UserDTO;
import com.example.client.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @GetMapping("/user-profile")
    public String userProfile(Model model) {
        List<Date> userHistories = userService.getUserHistory();
        model.addAttribute("userHistories", userHistories);
        return "user-profile";
    }

    @PostMapping(value = "/signUp",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String signUp(UserDTO userDTO) {
        userService.createUser(userDTO);
        return "redirect:/user-profile";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "user-profile";
    }
}
