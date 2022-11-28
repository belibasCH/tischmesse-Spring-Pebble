package com.example.Tischmesse;

import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.service.ExhibitorService;
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

    public static final String JSON_FILE = "exhibitor.json";

    private static final Logger logger = LoggerFactory.getLogger(SampleDataAdder.class);

    private final ObjectMapper mapper;
    private final ExhibitorService exhibitorService;

    public SampleDataAdder(ObjectMapper mapper,
                           ExhibitorService exhibitorService) {
        this.mapper = mapper;
        this.exhibitorService = exhibitorService;
    }

    @Override
    public void run(String... args) throws IOException {
        addSampleExhibitors();
    }

    public void addSampleExhibitors() throws IOException {
        if (exhibitorService.getExhibitorList().isEmpty()) {
            logger.info("Adding sample Exhibitors");
            loadSampleExhibitors(mapper).forEach(exhibitorService::add);
        }
    }

    public static List<Exhibitor> loadSampleExhibitors(ObjectMapper mapper) throws IOException {
        return mapper.readValue(SampleDataAdder.class.getResource(JSON_FILE),
                new TypeReference<List<Exhibitor>>() {});
    }
}
