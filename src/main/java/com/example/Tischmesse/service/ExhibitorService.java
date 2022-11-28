package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.repository.ExhibitorRepository;
import com.example.Tischmesse.repository.BranchenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExhibitorService {

    private ExhibitorRepository repo;
    private BranchenRepository branchenRepo;

    public ExhibitorService(ExhibitorRepository repo, BranchenRepository branchenRepo) {
        this.repo = repo;
        this.branchenRepo = branchenRepo;
    }
    private List<Exhibitor> exhibitorList = new ArrayList<Exhibitor>();

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
                             Optional<String> url) {
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
        repo.save(exhibitor);

    }

    public Exhibitor add(Exhibitor contact) {
        return repo.save(contact);
    }

    public void editExhibitor(String firmenname, int id, Optional<String> email, Optional<Integer> telefonNr, Optional<String> beschreibung, Optional<Integer> plz, Optional<String> ort, Optional<String> adresse, Optional<String> url) {
        var exhibitor = findExhibitorById(id).orElseThrow(ExhibitorNotFound::new);
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
        repo.save(exhibitor);
 }
    private static class ExhibitorNotFound extends RuntimeException {}
}
