package com.example.Tischmesse.service;


import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.model.Sector;
import com.example.Tischmesse.repository.ExhibitorRepository;
import com.example.Tischmesse.repository.SectorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public Optional<Exhibitor> findExhibitorById(int id) {
            return repo.findById(id);
    }

    public void deleteExhibitor(int id) {
        repo.deleteById(id);
    }

    public void addExhibitor(String firmenname,
                             Optional<String> email,
                             Optional<Integer> telefonNr,
                             Optional<String> beschreibung,
                             Optional<Integer> plz,
                             Optional<String> ort,
                             Optional<String> adresse,
                             Optional<String> url,
                             Optional<List<String>> sectors ){
        var exhibitor = new Exhibitor();
        exhibitor.setCompanyName(firmenname);
        exhibitor.setEmail(email.orElse(""));
        exhibitor.setTel(telefonNr.orElse(0));
        exhibitor.setDescription(beschreibung.orElse(""));
        exhibitor.setPLZ(plz.orElse(0));
        exhibitor.setLocation(ort.orElse(""));
        exhibitor.setAddress(adresse.orElse(""));
        exhibitor.setUrl(url.orElse(""));
        exhibitor.setAccepted(false);
        exhibitor.setPaid(false);
        exhibitor.setTableNr(0);
        exhibitor.setAnmdeldeDatum(LocalDate.now());
        List<String> stringList= sectors.orElse(Collections.emptyList());
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
            Optional<Integer> tel,
            Optional<String> description,
            Optional<Integer> plz,
            Optional<String> location,
            Optional<String> address,
            Optional<String> url,
            Optional<Boolean> paid,
            Optional<Boolean> accepted,
            Optional<List<String>> sectors) {
        var exhibitor = findExhibitorById(id).orElseThrow(ExhibitorNotFound::new);
        exhibitor.setCompanyName(companyName);
        exhibitor.setEmail(email.orElse(""));
        exhibitor.setTel(tel.orElse(0));
        exhibitor.setDescription(description.orElse(""));
        exhibitor.setPLZ(plz.orElse(0));
        exhibitor.setLocation(location.orElse(""));
        exhibitor.setAddress(address.orElse(""));
        exhibitor.setUrl(url.orElse(""));
        exhibitor.setAccepted(false);
        exhibitor.setPaid(false);
        exhibitor.setTableNr(0);
        exhibitor.setPaid(paid.orElse(false));
        exhibitor.setPaid(accepted.orElse(false));
        List<String> stringList= sectors.orElse(Collections.emptyList());
        List<Sector> sectorsList = stringList.stream().map(e -> sectorRepo.findSectorBySectorName(e)).toList();
        exhibitor.setSectors(sectorsList);
        repo.save(exhibitor);
 }
    private static class ExhibitorNotFound extends RuntimeException {}
}
