package com.example.Tischmesse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AusstellerController {


    @GetMapping("/aussteller")
    public String showAussteller() {
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
