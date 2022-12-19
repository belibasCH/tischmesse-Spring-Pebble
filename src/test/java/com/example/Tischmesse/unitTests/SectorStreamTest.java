package com.example.Tischmesse.unitTests;

import com.example.Tischmesse.model.Sector;
import com.example.Tischmesse.repository.SectorRepository;
import com.example.Tischmesse.service.ExhibitorService;
import com.example.Tischmesse.service.SectorService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

public class SectorStreamTest {

    @Test
    public void testUntickedSectorsMethod(){
        //given
        Sector branche1 = new Sector("branche1");
        Sector branche2 = new Sector("branche2");
        Sector branche3 = new Sector("branche3");
        Sector branche4 = new Sector("branche4");
        Sector branche5 = new Sector("branche5");

        SectorRepository sectorRepositoryMock = mock(SectorRepository.class);
        ExhibitorService exhibitorService = mock(ExhibitorService.class);
        SectorService sectorService = new SectorService(sectorRepositoryMock, exhibitorService);
        List<Sector> tickedSectors = List.of(branche1, branche2, branche3);
        List<Sector> allSectors = List.of(branche1, branche2, branche3, branche4, branche5);

        //when
        when(sectorRepositoryMock.findAll()).thenReturn(allSectors);
        List<Sector> untickedSectors = sectorService.getSectorListWithoutActive(tickedSectors);

        //then
        assertEquals(2 , untickedSectors.size());
        assertEquals(List.of(branche4, branche5), untickedSectors);
    }
}
