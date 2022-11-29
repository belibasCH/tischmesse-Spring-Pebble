package com.example.Tischmesse.service;


import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.model.Sector;
import com.example.Tischmesse.repository.ExhibitorRepository;
import com.example.Tischmesse.repository.SectorRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExhibitorService {

    private ExhibitorRepository repo;
    private SectorRepository sectorRepo;

    public ExhibitorService(ExhibitorRepository repo, SectorRepository sectorRepo) {
        this.repo = repo;
        this.sectorRepo = sectorRepo;
    }

    public List<Exhibitor> getExhibitorList() {
        return repo.findAll();
    }

    public List<Exhibitor> getActiveExhibitorList() {
        return repo.findAll().stream().filter(e -> e.getAccepted()).collect(Collectors.toList());
    }

    public Optional<Exhibitor> findExhibitorById(int id) {
        return repo.findById(id);
    }

    public void deleteExhibitor(int id) {
        repo.deleteById(id);
    }

    public void addExhibitor(String firmenname,
                             Optional<String> email,
                             Optional<String> telefonNr,
                             Optional<String> beschreibung,
                             Optional<Integer> plz,
                             Optional<String> ort,
                             Optional<String> adresse,
                             Optional<String> url,
                             Optional<List<String>> sectors) {
        var exhibitor = new Exhibitor();
        exhibitor.setCompanyName(firmenname);
        exhibitor.setEmail(email.orElse(""));
        exhibitor.setTel(telefonNr.orElse(""));
        exhibitor.setDescription(beschreibung.orElse(""));
        exhibitor.setPLZ(plz.orElse(0));
        exhibitor.setLocation(ort.orElse(""));
        exhibitor.setAddress(adresse.orElse(""));
        exhibitor.setUrl(url.orElse(""));
        exhibitor.setAccepted(false);
        exhibitor.setPaid(false);
        exhibitor.setTableNr(0);
        exhibitor.setRegistrationDate(LocalDate.now());
        List<String> stringList = sectors.orElse(Collections.emptyList());
        List<Sector> sectorsList = stringList.stream().map(e -> sectorRepo.findSectorBySectorName(e)).toList();
        exhibitor.setSectors(sectorsList);
        repo.save(exhibitor);

    }

    public Exhibitor add(Exhibitor exhibitor) {
        return repo.save(exhibitor);
    }

    public void editExhibitor(
            String companyName,
            int id,
            Optional<String> email,
            Optional<String> tel,
            Optional<String> description,
            Optional<Integer> plz,
            Optional<String> location,
            Optional<String> address,
            Optional<String> url,
            Optional<String> imageUrl,
            Optional<Boolean> paid,
            Optional<Boolean> accepted,
            Optional<List<String>> sectors,
            Optional<String> date) throws ParseException {

        var exhibitor = findExhibitorById(id).orElseThrow(ExhibitorNotFound::new);
        exhibitor.setCompanyName(companyName);
        exhibitor.setEmail(email.orElse(""));
        exhibitor.setTel(tel.orElse(""));
        exhibitor.setDescription(description.orElse(""));
        exhibitor.setPLZ(plz.orElse(0));
        exhibitor.setLocation(location.orElse(""));
        exhibitor.setAddress(address.orElse(""));
        exhibitor.setUrl(url.orElse(""));
        exhibitor.setImageUrl(imageUrl.orElse("https://www.tischmesse.sh/templates/yootheme/cache/logo_shch_farbig_wif_rgb-d74e8f7b.jpeg"));
        exhibitor.setTableNr(0);
        exhibitor.setPaid(paid.orElse(false));
        exhibitor.setAccepted(accepted.orElse(false));
        List<String> stringList = sectors.orElse(Collections.emptyList());
        List<Sector> sectorsList = stringList.stream().map(e -> sectorRepo.findSectorBySectorName(e)).collect(Collectors.toList());
        exhibitor.setSectors(sectorsList);
        exhibitor.setRegistrationDate(LocalDate.parse(date.orElse("2000-2-2")));
        repo.save(exhibitor);
    }


    private static class ExhibitorNotFound extends RuntimeException {
    }
}
