package com.example.Tischmesse;

import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.model.Sector;
import com.example.Tischmesse.service.ExhibitorService;
import com.example.Tischmesse.service.SectorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.IOException;
import java.util.List;

@Component
@ConditionalOnProperty("exhibitor-list.add-sample-exhibitor")
public class SampleDataAdder implements CommandLineRunner {

    public static final String JSON_FILE_EXHIBITORS = "exhibitor.json";
    public static final String JSON_FILE_SECTORS = "sector.json";

    private static final Logger logger = LoggerFactory.getLogger(SampleDataAdder.class);

    private final ObjectMapper mapper;
    private final ExhibitorService exhibitorService;
    private final SectorService sectorService;

    public SampleDataAdder(ObjectMapper mapper,
                           ExhibitorService exhibitorService, SectorService sectorService) {
        this.mapper = mapper;
        this.exhibitorService = exhibitorService;
        this.sectorService = sectorService;
    }

    @Override
    public void run(String... args) throws IOException {
        addSampleExhibitors();
        addSampleSectors();
    }

    private void addSampleSectors() throws IOException {
        if (sectorService.getSectorList().isEmpty()) {
            logger.info("Adding sample Sectors");
            loadSampleSectors(mapper).forEach(sectorService::add);
        }
    }

    public void addSampleExhibitors() throws IOException {
        if (exhibitorService.getExhibitorList().isEmpty()) {
            logger.info("Adding sample Exhibitors");
            loadSampleExhibitors(mapper).forEach(exhibitorService::add);
        }
    }



    public static List<Exhibitor> loadSampleExhibitors(ObjectMapper mapper) throws IOException {
        return mapper.readValue(SampleDataAdder.class.getResource(JSON_FILE_EXHIBITORS),
                new TypeReference<List<Exhibitor>>() {});
    }

    public static List<Sector> loadSampleSectors(ObjectMapper mapper) throws IOException {
        return mapper.readValue(SampleDataAdder.class.getResource(JSON_FILE_SECTORS),
                new TypeReference<List<Sector>>() {});
    }
}
