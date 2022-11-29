package com.example.Tischmesse.unitTests;

import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.repository.ExhibitorRepository;
import com.example.Tischmesse.repository.SectorRepository;
import com.example.Tischmesse.service.ExhibitorService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActiveExhibitorTest {

    @Test
    public void testActiveExhibitorList(){
        //given
        Exhibitor acceptedExhibitor = new Exhibitor("Accepted AG", "test@gmail.com", 791025487, "Unser neues Unternehmen ist...", LocalDate.now(), 102, 5000, "Aarau", "Bahnhofstrasse 4", "https://www.raiffeisen.ch/");
        acceptedExhibitor.setAccepted(true);
        Exhibitor notAcceptedExhibitor = new Exhibitor("Notaccepted AG", "test@gmail.com", 791025487, "Unser neues Unternehmen ist...", LocalDate.now(), 102, 5000, "Aarau", "Bahnhofstrasse 4", "https://www.raiffeisen.ch/");
        notAcceptedExhibitor.setAccepted(false);
        Exhibitor acceptedExhibitor2 = new Exhibitor("Secondaccepted AG", "test@gmail.com", 791025487, "Unser neues Unternehmen ist...", LocalDate.now(), 102, 5000, "Aarau", "Bahnhofstrasse 4", "https://www.raiffeisen.ch/");
        acceptedExhibitor2.setAccepted(true);

        List<Exhibitor> allExhibitors = List.of(acceptedExhibitor, notAcceptedExhibitor, acceptedExhibitor2);

        ExhibitorRepository exhibitorRepositoryMock = mock(ExhibitorRepository.class);
        SectorRepository sectorRepositoryMock = mock(SectorRepository.class);
        ExhibitorService exhibitorService = new ExhibitorService(exhibitorRepositoryMock, sectorRepositoryMock);

        //when
        when(exhibitorRepositoryMock.findAll()).thenReturn(allExhibitors);
        List<Exhibitor> resultList = exhibitorService.getActiveExhibitorList();

        //then
        assertEquals(2, resultList.size());
        assertEquals(List.of(acceptedExhibitor, acceptedExhibitor2), resultList);
    }
}
