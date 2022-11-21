package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Aussteller;
import com.example.Tischmesse.repository.AusstellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AusstellerService {

    private AusstellerRepository repo;

    public AusstellerService (AusstellerRepository repo) {
        this.repo = repo;
    }
    private List<Aussteller> ausstellerListe = new ArrayList<Aussteller>();

 public List<Aussteller> getAusstellerListe() {
     return repo.findAll();
 }

    public Optional<Aussteller> findAusstellerById(int id) {
            return repo.findById(id);
    }

    public void deleteAussteller(int id) {
        repo.deleteById(id);
    }

    public void addAussteller(String firmenname,
                              Optional<String> email,
                              Optional<Integer> telefonNr,
                              Optional<String> beschreibung,
                              Optional<Integer> plz,
                              Optional<String> ort,
                              Optional<String> adresse,
                              Optional<String> url) {
        var aussteller = new Aussteller();
        aussteller.setFirmenname(firmenname);
        aussteller.setEmail(email.orElse(""));
        aussteller.setTelefonNr(telefonNr.orElse(0));
        aussteller.setBeschreibung(beschreibung.orElse(""));
        aussteller.setPLZ(plz.orElse(0));
        aussteller.setOrt(ort.orElse(""));
        aussteller.setAdresse(adresse.orElse(""));
        aussteller.setUrl(url.orElse(""));
        aussteller.setAkzeptiert(false);
        aussteller.setBezahlt(false);
        aussteller.setTischNummer(0);
        aussteller.setAnmdeldeDatum(LocalDate.now());
        repo.save(aussteller);

    }

    public void editAussteller(String firmenname, int id, Optional<String> email, Optional<Integer> telefonNr, Optional<String> beschreibung, Optional<Integer> plz, Optional<String> ort, Optional<String> adresse, Optional<String> url) {
        var aussteller = findAusstellerById(id).orElseThrow(AusstellerNotFound::new);
        aussteller.setFirmenname(firmenname);
        aussteller.setEmail(email.orElse(""));
        aussteller.setTelefonNr(telefonNr.orElse(0));
        aussteller.setBeschreibung(beschreibung.orElse(""));
        aussteller.setPLZ(plz.orElse(0));
        aussteller.setOrt(ort.orElse(""));
        aussteller.setAdresse(adresse.orElse(""));
        aussteller.setUrl(url.orElse(""));
        aussteller.setAkzeptiert(false);
        aussteller.setBezahlt(false);
        aussteller.setTischNummer(0);
        aussteller.setAnmdeldeDatum(LocalDate.now());
        repo.save(aussteller);
 }
    private static class AusstellerNotFound extends RuntimeException {}
}
