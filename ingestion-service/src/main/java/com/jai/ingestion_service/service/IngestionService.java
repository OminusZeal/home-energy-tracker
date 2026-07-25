package com.jai.ingestion_service.service;
import com.jai.ingestion_service.dto.EnergyUsageDto;
import com.jai.kafka.event.EnergyUsageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.SortedSet;

@Slf4j
@Service
public class IngestionService {


    public final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    public IngestionService(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public void ingestEnergyUsage(EnergyUsageDto input){

        EnergyUsageEvent event=EnergyUsageEvent.builder()
                .deviceId(input.deviceId())
                .energyConsumed(input.energyConsumed())
                .timestamp(input.timestamp())
                .build();

        kafkaTemplate.send("energy-usage",event);
        log.info("Ingested Energy Usage Event :{}",event);
    }
}
