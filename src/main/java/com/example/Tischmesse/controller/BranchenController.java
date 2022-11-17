package com.example.Tischmesse.controller;

import com.example.Tischmesse.service.BranchenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BranchenController {

    private final BranchenService branchenService;


    public BranchenController(BranchenService branchenService) {this.branchenService = branchenService;}

    @GetMapping("/branchen")
    public String showBranchen(Model model){
        model.addAttribute("branchenListe", branchenService.getBranchenListe());
        return "/branchen";
    }

    @PostMapping("/branchen")
    public String showBranchen(@RequestParam String branchenname, Model model){
        model.addAttribute("branchenListe", branchenService.addBranche(branchenname));
        model.addAttribute("branchenListe", branchenService.getBranchenListe());
        return "/branchen";
    }



}
