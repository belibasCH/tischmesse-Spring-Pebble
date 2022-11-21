package com.example.Tischmesse.controller;

import com.example.Tischmesse.model.Aussteller;
import com.example.Tischmesse.service.AusstellerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class AusstellerController {

    private final AusstellerService service;

    public AusstellerController(AusstellerService service) {
        this.service = service;
    }
    @GetMapping("/aussteller")
    public String showAussteller(Model model) {
        model.addAttribute("ausstellerListe", service.getAusstellerListe());
        return "/aussteller";
    }
    @GetMapping("/aussteller/add")
    public String anmeldung() {
        return "/ausstellerForm";
    }

    @PostMapping("/aussteller/add")
    public String addAussteller(@RequestParam String firmenname,
                                @RequestParam Optional<String> email,
                                @RequestParam Optional<Integer> telefonNr,
                                @RequestParam Optional<String> beschreibung,
                                @RequestParam Optional<Integer> plz,
                                @RequestParam Optional<String> ort,
                                @RequestParam Optional<String> adresse,
                                @RequestParam Optional<String> url) {

        service.addAussteller(firmenname, email, telefonNr, beschreibung, plz, ort, adresse, url);
        return "redirect:/aussteller";
    }
    @PostMapping("/aussteller/edit")
    public String addAussteller(@RequestParam Integer id,
            @RequestParam String firmenname,
                                @RequestParam Optional<String> email,
                                @RequestParam Optional<Integer> telefonNr,
                                @RequestParam Optional<String> beschreibung,
                                @RequestParam Optional<Integer> plz,
                                @RequestParam Optional<String> ort,
                                @RequestParam Optional<String> adresse,
                                @RequestParam Optional<String> url) {

        service.editAussteller(firmenname,id,  email, telefonNr, beschreibung, plz, ort, adresse, url);
        return "redirect:/aussteller";
    }

    @GetMapping("/aussteller/{id}")
    public String anmeldung( @PathVariable int id, Model model
    ) {
        var aussteller = service.findAusstellerById(id).orElseThrow(AusstellerNotFound::new);
        model.addAttribute("aktuellerAussteller", aussteller);
        return "/ausstellerEinzeln";
    }

    @GetMapping("/aussteller/{id}/delete")
    public String addAussteller(@PathVariable int id)                      {
        service.deleteAussteller(id);
        return "redirect:/aussteller";
    }
    @GetMapping("/aussteller/{id}/edit")
    public String editAussteller(@PathVariable int id, Model model)                      {
        model.addAttribute("aktuellerAussteller", service.findAusstellerById(id).orElseThrow(AusstellerNotFound::new));
        return "/ausstellerEdit";
    }


    private static class AusstellerNotFound extends RuntimeException {}

}
