package com.example.Tischmesse.controller;

import com.example.Tischmesse.model.User;
import com.example.Tischmesse.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

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
        service.addUser(username, password, Collections.emptyList());
        return "redirect:/user";
    }
    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable int id,  Model model) {
        User user = service.findUserById(id).orElseThrow(InvalidUserName::new);
        model.addAttribute("currentUser", service.findUserById(id));
        return "/user-edit";
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

    @GetMapping("/user-edit")
    public String userEditMode(Model model, Authentication auth) {
        if (auth == null){
            return "/login";
        }
        User currentUser  = service.findUser(auth.getName()).orElseThrow(InvalidUserName::new);
        model.addAttribute("exhibitorList", currentUser.getExhibitors());
        return "/live-user-edit";
    }

    @ExceptionHandler(UserController.InvalidUserName.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidExhibitorName(Model model){
        return "notFound";
    }
    private static class InvalidUserName extends RuntimeException{}
}
