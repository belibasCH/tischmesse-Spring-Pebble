package com.example.Tischmesse.repository;

import com.example.Tischmesse.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Integer> {
    Sector findSectorById(int sector);

}
