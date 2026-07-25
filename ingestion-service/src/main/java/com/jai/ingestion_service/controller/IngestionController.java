package com.jai.ingestion_service.controller;

import com.jai.ingestion_service.dto.EnergyUsageDto;
import com.jai.ingestion_service.service.IngestionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class IngestionController {

    private final IngestionService service;

    public IngestionController(IngestionService service){
        this.service=service;
    }

    @PostMapping("/api/ingestion")
    @ResponseStatus(HttpStatus.CREATED)
    public void ingestData(@RequestBody EnergyUsageDto usageDto){
        service.ingestEnergyUsage(usageDto);
    }

}
