package com.example.Tischmesse.model;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Branche {

    @Id
    @GeneratedValue
    private String branchenName;


    @ElementCollection
    private List<String> ausstellerList;

    public Branche(String branchenName){
        this.branchenName = branchenName;
        ausstellerList = Collections.emptyList();
    }

    public Branche() {

    }

    public String getBranchenName(){
        return branchenName;
    }

    public void setBranchenName(String newName){
        branchenName = newName;
    }

    public List<String> getAusstellerList(){
        return ausstellerList;
    }

    public void addAusstellerToList(String branchenName){
        ausstellerList.add(branchenName);
    }

    public void removeAusstellerFromList(String branchenName){
        ausstellerList.remove(branchenName);
    }
}
