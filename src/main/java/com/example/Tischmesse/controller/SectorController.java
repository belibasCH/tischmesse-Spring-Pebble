package com.example.Tischmesse.controller;

import com.example.Tischmesse.service.ExhibitorService;
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

    private final ExhibitorService exhibitorService;

    public SectorController(SectorService sectorService, ExhibitorService exhibitorService) {
        this.sectorService = sectorService;
        this.exhibitorService = exhibitorService;
    }

    @GetMapping("/sectors")
    public String showSector(Model model){
        model.addAttribute("sectorList", sectorService.getMatchingExhibitorList());
        return "/sectors";
    }

    @PostMapping("/sectors/add")
    public String addSector(@RequestParam String sectorName, Model model){
            checkSectorName(sectorName);
            model.addAttribute("sectorList", sectorService.addSector(sectorName));
            model.addAttribute("sectorList", sectorService.getMatchingExhibitorList());
            return "/sectors";
    }
    @GetMapping("/sectors/add")
    public String addSectorRedirect(){
        return "redirect:/sectors";
    }


    @PostMapping("/sectors/delete")
    public String deleteSector(@RequestParam int id , Model model) {
        model.addAttribute("sectorList", sectorService.removeSector(id));
        model.addAttribute("sectorList", sectorService.getMatchingExhibitorList());
        return "/sectors";
    }
    @GetMapping("/sectors/delete")
    public String deleteSectorRedirect(){
        return "redirect:/sectors";
    }

    @PostMapping("/sectors/update")
    public String updateSectorName(@RequestParam int id, @RequestParam String sectorTitle, Model model){
        model.addAttribute("sectorList", sectorService.updateSector(id, sectorTitle));
        //model.addAttribute("sectorList", sectorService.getSectorList());
        model.addAttribute("sectorList", sectorService.getMatchingExhibitorList());
        return "/sectors";
    }

    @GetMapping("/sectors/update")
    public String updateSectorRedirect(){
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
