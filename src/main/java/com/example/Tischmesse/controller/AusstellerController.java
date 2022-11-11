package com.example.Tischmesse.controller;

import com.example.Tischmesse.service.AusstellerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AusstellerController {

    private final AusstellerService service;

    public AusstellerController(AusstellerService service) {
        this.service = service;
    }
    @GetMapping("/aussteller")
    public String showAussteller(Model model) {
        model.addAttribute("aussterllerListe", service.getAusstellerListe());
        return "/aussteller";
    }
    @GetMapping("/anmeldung")
    public String anmeldung() {
        return "/anmeldung";
    }
//    @PostMapping("/anmeldung")
//    public String addAussteller(@RequestParam String firstName,
//                             @RequestParam  String lastName,
//                             @RequestParam String jobTitle,
//                             @RequestParam String company) {
//        //var created =

//        return "redirect:/aussteller/" + created.getId();
//    }

}
