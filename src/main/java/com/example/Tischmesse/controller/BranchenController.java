package com.example.Tischmesse.controller;

import com.example.Tischmesse.service.BranchenService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    public String addBranchen(@RequestParam String branchenname, Model model){
            checkBranchenname(branchenname);
            model.addAttribute("branchenListe", branchenService.addBranche(branchenname));
            model.addAttribute("branchenListe", branchenService.getBranchenListe());
            return "/branchen";
    }


    @PostMapping("/branchen/{id}")
    public String deleteBranche(@PathVariable int id , Model model) {
        model.addAttribute("branchenListe", branchenService.removeBranche(id));
        model.addAttribute("branchenListe", branchenService.getBranchenListe());
        return "/branchen";
    }

//    @PostMapping("/branchen")
//    public String changeBranchenName(Model model){
//        model.addAttribute("branchenListe", branchenService.updateBranchenListe());
//        return "/branchen";
//    }

//    @GetMapping("/branchen")
//    public String filterBranche(Model model){
//        model.addAttribute("filteredBranchenListe", branchenService.filterBranchenListe());
//    }



    private void checkBranchenname(String branchenName){
        if(branchenName == null || branchenName.length() < 2 || branchenName.length() > 50){
            throw new InvalidBranchenName();
        }
    }

    @ExceptionHandler(InvalidBranchenName.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidBranchenName(Model model){
        model.addAttribute("branchenListe", branchenService.getBranchenListe());
        model.addAttribute("errorMessage", "Der Branchenname muss zwischen 3 und 50 Zeichen lang sein.");
        return "/branchen";
    }

    private static class InvalidBranchenName extends RuntimeException{}

}
