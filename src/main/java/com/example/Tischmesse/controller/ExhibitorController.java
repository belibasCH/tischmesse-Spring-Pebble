package com.example.Tischmesse.controller;

import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.model.User;
import com.example.Tischmesse.service.ExhibitorService;
import com.example.Tischmesse.service.SectorService;
import com.example.Tischmesse.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ExhibitorController {

    private final ExhibitorService exhibitorService;
    private final SectorService sectorService;
    private final UserService userService;

    public ExhibitorController(ExhibitorService aservice, SectorService bservice, UserService uservice) {
        this.exhibitorService = aservice;
        this.sectorService = bservice;
        this.userService = uservice;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("exhibitorList", exhibitorService.getActiveExhibitorList());
        return "/home";
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
                               @RequestParam Optional<String> tel,
                               @RequestParam Optional<String> description,
                               @RequestParam Optional<Integer> plz,
                               @RequestParam Optional<String> location,
                               @RequestParam Optional<String> address,
                               @RequestParam Optional<String> url,
                               @RequestParam Optional<String> imageUrl,
                               @RequestParam Optional<List<String>> sectors,
                               @RequestParam String username,
                               @RequestParam String pw) {
        checkExhibitornameName(companyName);
        Exhibitor newExhibitor = exhibitorService.addExhibitor(companyName, email, tel, description, plz, location, address, url, imageUrl, sectors);
        List<Exhibitor> exhibitorList = new ArrayList<Exhibitor>();
        exhibitorList.add(newExhibitor);
        userService.addUser(username, pw, exhibitorList);

        return "redirect:/confirmation";
    }

    @PostMapping("/exhibitor/edit")
    public String editExhibitor(@RequestParam Integer id,
                               @RequestParam String companyName,
                               @RequestParam Optional<String> email,
                               @RequestParam Optional<String> tel,
                               @RequestParam Optional<String> description,
                               @RequestParam Optional<Integer> plz,
                                @RequestParam Optional<Integer> tableNr,
                               @RequestParam Optional<String> location,
                               @RequestParam Optional<String> address,
                               @RequestParam Optional<String> url,
                               @RequestParam Optional<String> imageUrl,
                               @RequestParam Optional<Boolean> paid,
                               @RequestParam Optional<Boolean> accepted,
                               @RequestParam Optional<List<String>> sectors,
                               @RequestParam Optional<String> date
    ) throws ParseException {
        exhibitorService.editExhibitor(companyName, id, email, tel, description, plz,  tableNr, location, address, url, imageUrl, paid, accepted, sectors, date);
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
    public String deleteExhibitor(@PathVariable int id) {
        exhibitorService.deleteExhibitor(id);
        return "redirect:/exhibitor";
    }

    @GetMapping("/confirmation")
    public String showConfirmation() {
        return "confirmation";
    }

    @GetMapping("/exhibitor/{id}/edit")
    public String editExhibitor(@PathVariable int id, Model model) {
        Exhibitor current = exhibitorService.findExhibitorById(id).orElseThrow(ExhibitorNotFound::new);
        model.addAttribute("currentExhibitor", current);
        model.addAttribute("sectorList", sectorService.getSectorListWithoutActive(current.getSectors()));
        return "/exhibitor-edit";
    }

    private void checkExhibitornameName(String exName) {
        if (exhibitorService.doesExhibitorExist(exName) || exName.length()<1) {
            throw new InvalidExhibitorName();
        }
    }

    @ExceptionHandler(ExhibitorController.InvalidExhibitorName.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidExhibitorName(Model model){
        model.addAttribute("errorMessage", "Es ist bereits eine Firma mit diesem Namen angemeldet");
        model.addAttribute("sectorList", sectorService.getSectorList());
        return "/exhibitor-form";
    }

    private static class InvalidExhibitorName extends RuntimeException{}
    private static class ExhibitorNotFound extends RuntimeException {}

}
