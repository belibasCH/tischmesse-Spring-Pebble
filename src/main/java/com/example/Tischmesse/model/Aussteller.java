package com.example.Tischmesse.model;

import java.time.LocalDate;

public class Aussteller {

    private String firmenname;
    private String email;
    private Integer telefonNr;
    private String beschreibung;
    private LocalDate anmeldeDatum;
    private Integer tischNummer;
    private Integer plz;
    private String ort;
    private String adresse;
    private String url;

    private Boolean bezahlt;
    private Boolean akzeptiert;

    //Branche Objekt
    //Liste mit Personen

    public Aussteller(String firmenname, String email, Integer telefonNr, String beschreibung, LocalDate anmeldeDatum, Integer tischNummer, Integer plz, String ort, String adresse, String url) {
        this.firmenname = firmenname;
        this.email = email;
        this.telefonNr = telefonNr;
        this.beschreibung = beschreibung;
        this.anmeldeDatum = anmeldeDatum;
        this.tischNummer = tischNummer;
        this.plz = plz;
        this.ort = ort;
        this.adresse = adresse;
        this.url = url;
        this.bezahlt = false;
        this.akzeptiert = false;
    }

    public String getFirmenname() {
        return firmenname;
    }

    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(int telefonNr) {
        this.telefonNr = telefonNr;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public LocalDate getAnmdeldeDatum() {
        return anmeldeDatum;
    }

    public void setAnmdeldeDatum(LocalDate anmeldeDatum) {
        this.anmeldeDatum = anmeldeDatum;
    }

    public Integer getTischNummer() {
        return tischNummer;
    }

    public void setTischNummer(int tischNummer) {
        this.tischNummer = tischNummer;
    }

    public Integer getPLZ() {
        return plz;
    }

    public void setPLZ(int PLZ) {
        this.plz = PLZ;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        ort = ort;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        adresse = adresse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(Boolean bezahlt) {
        this.bezahlt = bezahlt;
    }

    public Boolean getAkzeptiert() {
        return akzeptiert;
    }

    public void setAkzeptiert(Boolean akzeptiert) {
        this.akzeptiert = akzeptiert;
    }


}
