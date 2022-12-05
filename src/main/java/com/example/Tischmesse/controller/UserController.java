package com.example.Tischmesse.controller;


import com.example.Tischmesse.service.UserService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    @GetMapping("/user/add")
    public String registerUser() {
        return "user-add";
    }
    @PostMapping("/user/add")
    public String addUser(@RequestParam String username,
                         @RequestParam String password){
        service.addUser(username, password);
        return "redirect:/user";
    }
    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable int id,  Model model) {
        model.addAttribute("currentUser", service.findUserById(id));
        return "user-edit";
    }
    @PostMapping("/user/edit")
    public String addUser(@RequestParam int id,
                          @RequestParam String username,
                          @RequestParam String password){
        service.editUser(id, username, password);
        return "redirect:/user";
    }
    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        service.deleteUser(id);

        return "redirect:/user";
    }
}