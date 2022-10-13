package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.repository.RaceStatsRepository;
import org.springframework.stereotype.Service;

@Service
public class RaceStatsService {

    private final RaceStatsRepository repository;

    public RaceStatsService(RaceStatsRepository repository) {
        this.repository = repository;
    }

    public RaceStats mapStatsFromDto (RaceWriteDto statsOfRace) {
        RaceStats mappedStats = new RaceStats(statsOfRace.getBasicWs(), statsOfRace.getBasicBs(), statsOfRace.getBasicStr(),
                statsOfRace.getBasicTo(), statsOfRace.getBasicAg(), statsOfRace.getBasicInt(), statsOfRace.getBasicWp(),
                statsOfRace.getBasicFel(),statsOfRace.getMaxWounds(), statsOfRace.getMovement());
        return mappedStats;
    }
}