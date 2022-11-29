package com.example.Tischmesse.controller;

import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.service.ExhibitorService;
import com.example.Tischmesse.service.SectorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ExhibitorController {

    private final ExhibitorService exhibitorService;
    private final SectorService sectorService;

    public ExhibitorController(ExhibitorService aservice, SectorService bservice) {
        this.exhibitorService = aservice;
        this.sectorService = bservice;
    }
    @GetMapping("/exhibitor")
    public String showExhibitor(Model model) {
        model.addAttribute("exhibitorList", exhibitorService.getExhibitorList());
        return "/exhibitor";
    }
    @GetMapping("/exhibitor/add")
    public String findExhibitor(Model model) {
        model.addAttribute("sectorList", sectorService.getSectorList());
        return "/exhibitor-form";
    }

    @PostMapping("/exhibitor/add")
    public String addExhibitor(@RequestParam String companyName,
                               @RequestParam Optional<String> email,
                               @RequestParam Optional<Integer> tel,
                               @RequestParam Optional<String> description,
                               @RequestParam Optional<Integer> plz,
                               @RequestParam Optional<String> location,
                               @RequestParam Optional<String> address,
                               @RequestParam Optional<String> url,
                               @RequestParam Optional<List<String>> sectors) {
       exhibitorService.addExhibitor(companyName, email, tel, description, plz, location, address, url, sectors);

        return "redirect:/confirmation";
    }
    @PostMapping("/exhibitor/edit")
    public String addExhibitor(@RequestParam Integer id,
                               @RequestParam String companyName,
                               @RequestParam Optional<String> email,
                               @RequestParam Optional<Integer> tel,
                               @RequestParam Optional<String> description,
                               @RequestParam Optional<Integer> plz,
                               @RequestParam Optional<String> location,
                               @RequestParam Optional<String> address,
                               @RequestParam Optional<String> url,
                               @RequestParam Optional<Boolean> paid,
                               @RequestParam Optional<Boolean> accepted,
                               @RequestParam Optional<List<String>> sectors
                               ) {

        exhibitorService.editExhibitor(companyName,id,  email, tel, description, plz, location, address, url, paid, accepted, sectors);
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
    @GetMapping("/confirmation")
    public String showConfirmation(){
        return "confirmation";
    }
    @GetMapping("/exhibitor/{id}/edit")
    public String editExhibitor(@PathVariable int id, Model model)                      {
        Exhibitor current = exhibitorService.findExhibitorById(id).orElseThrow(ExhibitorNotFound::new);
        model.addAttribute("currentExhibitor", current);
        model.addAttribute("sectorList", sectorService.getSectorListWithoutActive(current.getSectors()));
        return "/exhibitor-edit";
    }

    private static class ExhibitorNotFound extends RuntimeException {}

}
