package com.example.Tischmesse.controller;

import com.example.Tischmesse.service.ExhibitorService;
import com.example.Tischmesse.service.BranchenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ExhibitorController {

    private final ExhibitorService exhibitorService;
    private final BranchenService branchenService;

    public ExhibitorController(ExhibitorService aservice, BranchenService bservice) {
        this.exhibitorService = aservice;
        this.branchenService = bservice;
    }
    @GetMapping("/exhibitor")
    public String showExhibitor(Model model) {
        model.addAttribute("exhibitorList", exhibitorService.getExhibitorList());
        return "/exhibitor";
    }
    @GetMapping("/exhibitor/add")
    public String findExhibitor(Model model) {
        model.addAttribute("branchenListe", branchenService.getBranchenListe());
        return "/exhibitor-form";
    }

    @PostMapping("/exhibitor/add")
    public String addExhibitor(@RequestParam String firmenname,
                               @RequestParam Optional<String> email,
                               @RequestParam Optional<Integer> telefonNr,
                               @RequestParam Optional<String> beschreibung,
                               @RequestParam Optional<Integer> plz,
                               @RequestParam Optional<String> ort,
                               @RequestParam Optional<String> adresse,
                               @RequestParam Optional<String> url) {

        exhibitorService.addExhibitor(firmenname, email, telefonNr, beschreibung, plz, ort, adresse, url);

        return "redirect:/exhibitor";
    }
    @PostMapping("/exhibitor/edit")
    public String addExhibitor(@RequestParam Integer id,
                               @RequestParam String firmenname,
                               @RequestParam Optional<String> email,
                               @RequestParam Optional<Integer> telefonNr,
                               @RequestParam Optional<String> beschreibung,
                               @RequestParam Optional<Integer> plz,
                               @RequestParam Optional<String> ort,
                               @RequestParam Optional<String> adresse,
                               @RequestParam Optional<String> url,
                               @RequestParam Optional<String> branche) {
        exhibitorService.editExhibitor(firmenname,id,  email, telefonNr, beschreibung, plz, ort, adresse, url);
        return "redirect:/exhibitor";
    }

    @GetMapping("/exhibitor/{id}")
    public String findExhibitor(@PathVariable int id, Model model
    ) {
        var aussteller = exhibitorService.findExhibitorById(id).orElseThrow(ExhibitorNotFound::new);
        model.addAttribute("currentExhibitor", aussteller);
        return "/exhibitor-view";
    }

    @GetMapping("/exhibitor/{id}/delete")
    public String addExhibitor(@PathVariable int id)                      {
        exhibitorService.deleteExhibitor(id);
        return "redirect:/exhibitor";
    }
    @GetMapping("/exhibitor/{id}/edit")
    public String editExhibitor(@PathVariable int id, Model model)                      {
        model.addAttribute("currentExhibitor", exhibitorService.findExhibitorById(id).orElseThrow(ExhibitorNotFound::new));
        model.addAttribute("branchenListe", branchenService.getBranchenListe());
        return "/exhibitor-edit";
    }


    private static class ExhibitorNotFound extends RuntimeException {}

}
