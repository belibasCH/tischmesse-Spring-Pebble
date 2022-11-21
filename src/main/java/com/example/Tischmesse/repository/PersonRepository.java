package com.example.Tischmesse.repository;

import com.example.Tischmesse.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
