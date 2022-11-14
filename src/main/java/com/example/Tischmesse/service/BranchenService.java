package com.example.Tischmesse.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchenService {

    private List<String> branchenListe = new ArrayList<>();

    public List<String> getBranchenListe(){
        branchenListe.add("Autobranche");
        return branchenListe;
    }

    public List<String> addBranche(String branche){
        branchenListe.add(branche);
        return branchenListe;
    }

    public List<String> removeBranche(String branche){
        branchenListe.remove(branche);
        return branchenListe;
    }
}
