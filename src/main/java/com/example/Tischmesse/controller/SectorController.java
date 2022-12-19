package com.example.Tischmesse.controller;

import com.example.Tischmesse.service.SectorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/sectors")
    public String showSector(Model model){
        model.addAttribute("sectorList", sectorService.getMatchingExhibitorList());
        return "/sectors";
    }

    @PostMapping("/sectors/add")
    public String addSector(@RequestParam String sectorName){
            checkSectorName(sectorName);
            sectorService.addSector(sectorName);
            return "redirect:/sectors";
    }

    @PostMapping("/sectors/delete")
    public String deleteSector(@RequestParam int id) {
        sectorService.removeSector(id);
        return "redirect:/sectors";
    }

    @PostMapping("/sectors/update")
    public String updateSectorName(@RequestParam int id, @RequestParam String sectorTitle){
        sectorService.updateSector(id, sectorTitle);
        return "redirect:/sectors";
    }

    private void checkSectorName(String sectorName){
        if(sectorName == null || sectorName.length() < 2 || sectorName.length() > 50){
            throw new InvalidSectorName();
        }
    }

    @ExceptionHandler(InvalidSectorName.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidSectorName(Model model){
        model.addAttribute("sectorList", sectorService.getMatchingExhibitorList());
        model.addAttribute("errorMessage", "Der Branchenname muss zwischen 3 und 50 Zeichen lang sein.");
        return "/sectors";
    }

    private static class InvalidSectorName extends RuntimeException{}
}
