package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Aussteller;
import com.example.Tischmesse.repository.AusstellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public Aussteller addAussteller(Aussteller newAussteller) {
        repo.save(newAussteller);
        return newAussteller;
    }

}
