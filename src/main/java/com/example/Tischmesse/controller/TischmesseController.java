package com.example.Tischmesse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TischmesseController {

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }
}
