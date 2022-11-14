package com.example.Tischmesse.controller;

import com.example.Tischmesse.model.Aussteller;
import com.example.Tischmesse.service.AusstellerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

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
    @GetMapping("/aussteller/add")
    public String anmeldung() {
        return "/ausstellerForm";
    }

    @PostMapping("/aussteller/add")
    public String addAussteller(@RequestParam String firmenname,
                                @RequestParam String email,
                                @RequestParam int telefonNr,
                                @RequestParam String beschreibung,
                                @RequestParam int plz,
                                @RequestParam String ort,
                                @RequestParam String adresse,
                                @RequestParam String url) {
        LocalDate currentDate = LocalDate.now(); // Create a date object
        int tischNummer = 0;
        service.addAussteller(new Aussteller(firmenname, email, telefonNr, beschreibung, currentDate, tischNummer, plz, ort, adresse,url));
        return "redirect:/aussteller";
    }

}
