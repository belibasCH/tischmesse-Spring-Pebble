package com.example.Tischmesse.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Exhibitor {

    @Id
    @GeneratedValue
    private int id;
    private String companyName;
    private String email;
    private String tel;
    private String description;
    private LocalDate registrationDate;
    private Integer tableNr;
    private Integer plz;
    private String location;
    private String address;
    private String url;
    private Boolean paid;
    private Boolean accepted;

    //Listen mit Branchen
    @OneToMany
    private List<Sector> sectors;

    //Liste mit Personen

    public Exhibitor(String companyName, String email, String tel, String description, LocalDate registrationDate, Integer tableNr, Integer plz, String location, String adress, String url) {
        this.companyName = companyName;
        this.email = email;
        this.tel = tel;
        this.description = description;
        this.registrationDate = registrationDate;
        this.tableNr = tableNr;
        this.plz = plz;
        this.location = location;
        this.address = adress;
        this.url = url;
        this.paid = false;
        this.accepted = false;
    }

    public Exhibitor() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String firmenname) {
        this.companyName = firmenname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String telefonNr) {
        this.tel = telefonNr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String beschreibung) {
        this.description = beschreibung;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate anmeldeDatum) {
        this.registrationDate = anmeldeDatum;
    }

    public Integer getTableNr() {
        return tableNr;
    }

    public void setTableNr(int tischNummer) {
        this.tableNr = tischNummer;
    }

    public Integer getPLZ() {
        return plz;
    }

    public void setPLZ(int PLZ) {
        this.plz = PLZ;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String ort) {
        this.location = ort;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adresse) {
        this.address = adresse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean bezahlt) {
        this.paid = bezahlt;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean akzeptiert) {
        this.accepted = akzeptiert;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

}
