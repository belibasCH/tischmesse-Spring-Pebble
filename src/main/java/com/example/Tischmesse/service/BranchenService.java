package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Branche;
import com.example.Tischmesse.repository.BranchenRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchenService {

    private BranchenRepository repo;

    public BranchenService(BranchenRepository repo){ this.repo = repo;}

    private List<Branche> brancheListe = new ArrayList<>();

    public List<Branche> getBranchenListe(){
        return repo.findAll();
    }

    public List<Branche> addBranche(String branche){
            Branche neueBranche = new Branche(branche);
            repo.save(neueBranche);
        return repo.findAll();
    }

    public List<Branche> removeBranche(int branchenId){
        repo.deleteById(branchenId);
        return repo.findAll();
    }

    public Optional<Branche> findBrancheById(int id){ return repo.findById(id);}

//    private List<String> branchenListe = new ArrayList<>();
//
//    public List<String> getBranchenListe(){
//        return branchenListe;
//    }
//
//    public List<String> addBranche(String branche){
//        branchenListe.add(branche);
//        return branchenListe;
//    }
//
//    public List<String> removeBranche(String branche){
//        branchenListe.remove(branche);
//        return branchenListe;
//    }
}
