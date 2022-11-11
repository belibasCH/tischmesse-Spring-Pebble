package com.example.Tischmesse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TischmesseController {

    @GetMapping("/admin")
    public String getAdminOverwiew() {
        return "/admin";
    }


    @GetMapping("/")
    public String home(){ return "/home";}

}
