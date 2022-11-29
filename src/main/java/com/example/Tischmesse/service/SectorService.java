package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Sector;
import com.example.Tischmesse.repository.SectorRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectorService {

    private SectorRepository repo;

    public SectorService(SectorRepository repo){ this.repo = repo;}

    public List<Sector> getSectorList(){
        return repo.findAll();
    }
    public List<Sector> getSectorListWithoutActive(List<Sector> mysectors){
        List<Sector> allSectors = repo.findAll();
        return allSectors.stream().filter(item -> !mysectors.contains(item)).collect(Collectors.toList());
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

    public List<Sector> updateSector(int id, String newSectorName) {

        Sector currentSector = repo.findById(id).orElse(new Sector());
        currentSector.setSectorName(newSectorName);
        repo.save(currentSector);
        return repo.findAll();
    }
    public Sector add(Sector sector) {
        return repo.save(sector);
    }
}
