package com.example.Tischmesse.controller;

import com.example.Tischmesse.service.ExhibitorService;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class TischmesseController {

    @GetMapping("/admin")
    public String getAdminOverwiew() {
        return "/admin";
    }

    @GetMapping("/about")
    public String getAboutPage(){ return "/about";}


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        return "/404";
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(BAD_REQUEST)
    public String badRequest(Model model) {
        return "/404";
    }


}
