package com.example.Tischmesse.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sector {

    @Id
    @GeneratedValue
    private int id;

    private String sectorName;

    public Sector(String sectorName){
        this.sectorName = sectorName;
    }

    public Sector() {

    }


    public String getSectorName(){
        return sectorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSectorName(String newSectorName){
        this.sectorName = newSectorName;
    }
}
