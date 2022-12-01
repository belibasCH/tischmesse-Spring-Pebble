package com.example.Tischmesse.controller;


import com.example.Tischmesse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    private final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public String loginPage(Model model) {
        model.addAttribute("userList",service.findAllUsers());

        return "user";
    }
}
