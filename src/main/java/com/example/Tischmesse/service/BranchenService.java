package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Aussteller;
import com.example.Tischmesse.model.Branche;
import com.example.Tischmesse.repository.AusstellerRepository;
import com.example.Tischmesse.repository.BranchenRepository;
import org.apache.el.stream.Stream;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchenService {

    private BranchenRepository repo;
    private AusstellerRepository exhibitorRepo;

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

//    public List<Branche> updateBranchenListe(Branche brancheToUpdate) {
//        brancheToUpdate.setBranchenName(brancheToUpdate.getBranchenName());
//        //repo.getReferenceById(brancheToUpdate.getId());
//    }

//    public List<Branche> filterBranchenListe() {
//        exhibitorRepo.findAll().stream()
//            .filter(a -> a.)
//    }
}
