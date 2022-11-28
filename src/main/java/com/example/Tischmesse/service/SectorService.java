package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Sector;
import com.example.Tischmesse.repository.SectorRepository;
import com.example.Tischmesse.repository.ExhibitorRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectorService {

    private SectorRepository repo;

    public SectorService(SectorRepository repo){ this.repo = repo;}

    private List<Sector> sectorList = new ArrayList<>();

    public List<Sector> getSectorList(){
        return repo.findAll();
    }

    public List<Sector> addSector(String sector){
            Sector newSector = new Sector(sector);
            repo.save(newSector);
        return repo.findAll();
    }

    public List<Sector> removeSector(int sectorId){
        repo.deleteById(sectorId);
        return repo.findAll();
    }

    public Optional<Sector> findSectorById(int id){ return repo.findById(id);}

}
