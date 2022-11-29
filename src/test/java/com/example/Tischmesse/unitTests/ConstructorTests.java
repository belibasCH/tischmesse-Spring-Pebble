package com.example.Tischmesse.unitTests;

import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.model.Sector;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTests {

    @Test
    public void testSectorConstructorWithGivenName(){
        //given
        Sector sectorUnderTest = new Sector("Fahrzeuge");

        //then
        assertEquals("Fahrzeuge", sectorUnderTest.getSectorName());
    }

    @Test
    public void testExhibitorConsrtructor(){
        //given
        Exhibitor exhibitorUnderTest = new Exhibitor("Test AG", "test@gmail.com", 791025487, "Unser neues Unternehmen ist...", LocalDate.now(), 102, 5000, "Aarau", "Bahnhofstrasse 4", "https://www.raiffeisen.ch/");

        //then
        assertEquals("Test AG", exhibitorUnderTest.getCompanyName());
        assertEquals("test@gmail.com", exhibitorUnderTest.getEmail());
        assertEquals(791025487, exhibitorUnderTest.getTel());
        assertEquals("Unser neues Unternehmen ist...", exhibitorUnderTest.getDescription());
        assertEquals(LocalDate.class , exhibitorUnderTest.getAnmdeldeDatum().getClass());
        assertEquals(102, exhibitorUnderTest.getTableNr());
        assertEquals(5000, exhibitorUnderTest.getPLZ());
        assertEquals("Aarau", exhibitorUnderTest.getLocation());
        assertEquals("Bahnhofstrasse 4", exhibitorUnderTest.getAddress());
        assertEquals("https://www.raiffeisen.ch/", exhibitorUnderTest.getUrl());
    }
}
