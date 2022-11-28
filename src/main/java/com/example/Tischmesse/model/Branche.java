package com.example.Tischmesse.model;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collections;
import java.util.List;

@Entity
public class Branche {

    @Id
    @GeneratedValue
    private int id;

    private String branchenName;

    public Branche(String branchenName){
        this.branchenName = branchenName;
    }

    public Branche() {

    }


    public String getBranchenName(){
        return branchenName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBranchenName(String newName){
        branchenName = newName;
    }
}
