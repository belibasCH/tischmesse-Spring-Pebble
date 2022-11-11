package com.example.Tischmesse.model;

public class Aussteller {

    private String firmenname;
    private String email;
    private int telefonNr;
    private String beschreibung;
    private int anmdeldeDatum;
    private int tischNummer;
    private int PLZ;
    private String Ort;
    private String Adresse;
    private String url;

    private Boolean bezahlt;
    private Boolean akzeptiert;

    //Branche Objekt
    //Liste mit Personen

    public Aussteller(String firmenname, String email, int telefonNr, String beschreibung, int anmdeldeDatum, int tischNummer, int PLZ, String ort, String adresse, String url) {
        this.firmenname = firmenname;
        this.email = email;
        this.telefonNr = telefonNr;
        this.beschreibung = beschreibung;
        this.anmdeldeDatum = anmdeldeDatum;
        this.tischNummer = tischNummer;
        this.PLZ = PLZ;
        Ort = ort;
        Adresse = adresse;
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

    public int getTelefonNr() {
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

    public int getAnmdeldeDatum() {
        return anmdeldeDatum;
    }

    public void setAnmdeldeDatum(int anmdeldeDatum) {
        this.anmdeldeDatum = anmdeldeDatum;
    }

    public int getTischNummer() {
        return tischNummer;
    }

    public void setTischNummer(int tischNummer) {
        this.tischNummer = tischNummer;
    }

    public int getPLZ() {
        return PLZ;
    }

    public void setPLZ(int PLZ) {
        this.PLZ = PLZ;
    }

    public String getOrt() {
        return Ort;
    }

    public void setOrt(String ort) {
        Ort = ort;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
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
