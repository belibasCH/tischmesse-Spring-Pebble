package com.example.Tischmesse.controller;

import com.example.Tischmesse.service.AusstellerService;
import com.example.Tischmesse.service.BranchenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AusstellerController {

    private final AusstellerService ausstellerService;
    private final BranchenService branchenService;

    public AusstellerController(AusstellerService aservice, BranchenService bservice) {
        this.ausstellerService = aservice;
        this.branchenService = bservice;
    }
    @GetMapping("/aussteller")
    public String showAussteller(Model model) {
        model.addAttribute("ausstellerListe", ausstellerService.getAusstellerListe());
        return "/exhibitor";
    }
    @GetMapping("/aussteller/add")
    public String anmeldung(Model model) {
        model.addAttribute("branchenListe", branchenService.getBranchenListe());
        return "/exhibitor-form";
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

        ausstellerService.addAussteller(firmenname, email, telefonNr, beschreibung, plz, ort, adresse, url);

        return "redirect:/exhibitor";
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
                                @RequestParam Optional<String> url,
    @RequestParam Optional<String> branche) {
        ausstellerService.editAussteller(firmenname,id,  email, telefonNr, beschreibung, plz, ort, adresse, url);
        return "redirect:/exhibitor";
    }

    @GetMapping("/aussteller/{id}")
    public String anmeldung( @PathVariable int id, Model model
    ) {
        var aussteller = ausstellerService.findAusstellerById(id).orElseThrow(ExhibitorNotFound::new);
        model.addAttribute("aktuellerAussteller", aussteller);

        return "/exhibitor-view";
    }

    @GetMapping("/aussteller/{id}/delete")
    public String addAussteller(@PathVariable int id)                      {
        ausstellerService.deleteAussteller(id);
        return "redirect:/exhibitor";
    }
    @GetMapping("/aussteller/{id}/edit")
    public String editAussteller(@PathVariable int id, Model model)                      {
        model.addAttribute("aktuellerAussteller", ausstellerService.findAusstellerById(id).orElseThrow(ExhibitorNotFound::new));
        model.addAttribute("branchenListe", branchenService.getBranchenListe());
        return "/exhibitor-edit";
    }


    private static class ExhibitorNotFound extends RuntimeException {}

}
