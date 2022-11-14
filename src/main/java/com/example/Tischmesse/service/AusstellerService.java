package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Aussteller;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AusstellerService {
    private List<Aussteller> ausstellerListe = new ArrayList<Aussteller>();

 public List<Aussteller> getAusstellerListe() {
     ausstellerListe.add(new Aussteller(
             "elias AG",
             "elias.braem@students.fhnw.ch" ,
             79,
             "Beschreibungstext",
             LocalDate.now(),
             20,
             8200,
             "Schaffhausen",
             "Im Riet 6",
             "fhnw.ch"
             ));
     return ausstellerListe;
 }
    public List<Aussteller> addAussteller(Aussteller newAussteller) {
        ausstellerListe.add(newAussteller);
        return ausstellerListe;
    }

}
