package com.example.Tischmesse.unitTests;

import com.example.Tischmesse.SampleDataAdder;
import com.example.Tischmesse.repository.ExhibitorRepository;
import com.example.Tischmesse.repository.SectorRepository;
import com.example.Tischmesse.service.ExhibitorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

 class SampleDataAdderTest {


    ExhibitorService service;

    SampleDataAdderTest() throws IOException {
        var mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        var exhibitors = SampleDataAdder.loadSampleExhibitors(mapper);
        var sectors = SampleDataAdder.loadSampleSectors(mapper);

        var stubExhibitor = Mockito.mock(ExhibitorRepository.class);
        when(stubExhibitor.findAll()).thenReturn(exhibitors);
        var stubSector = Mockito.mock(SectorRepository.class);
        when(stubSector.findAll()).thenReturn(sectors);
        service = new ExhibitorService(stubExhibitor, stubSector);
    }

     @Test
     void exhibitorListName() {
         var exhibitorList = service.getExhibitorList();
         assertNotNull(exhibitorList);
         Assertions.assertFalse(exhibitorList.isEmpty());
         Assertions.assertEquals("Elias AG", exhibitorList.get(0).getCompanyName());
     }
 }

