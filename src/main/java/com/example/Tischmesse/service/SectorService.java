package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.model.Sector;
import com.example.Tischmesse.repository.SectorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectorService {

    private SectorRepository repo;

    private ExhibitorService exhibitorService;

    public SectorService(SectorRepository repo, ExhibitorService exhibitorService){
        this.repo = repo;
        this.exhibitorService = exhibitorService;}

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


    public Map<Sector, List<Exhibitor>> getMatchingExhibitorList() {
        Map<Sector, List<Exhibitor>> mapOfExhibitors = new LinkedHashMap<>();
        for(int i = 0; i < repo.findAll().size(); i++){
            int helperVariable = i;
            Sector currentSector = repo.findAll().get(i);
            mapOfExhibitors.put(currentSector,
                exhibitorService.getExhibitorList().stream().filter(e -> e.getSectors().contains(currentSector)).sorted(Comparator.comparing(Exhibitor::getCompanyName)).toList());
        }

        return mapOfExhibitors;
    }
}
