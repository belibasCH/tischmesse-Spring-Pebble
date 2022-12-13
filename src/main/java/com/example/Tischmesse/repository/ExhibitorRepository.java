package com.example.Tischmesse.repository;

import com.example.Tischmesse.model.Exhibitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitorRepository extends JpaRepository<Exhibitor, Integer> {
    Exhibitor findExhibitorByCompanyName(String name);
}
