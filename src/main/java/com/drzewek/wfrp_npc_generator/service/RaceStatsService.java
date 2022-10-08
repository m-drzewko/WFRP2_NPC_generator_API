package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.repository.RaceStatsRepository;
import org.springframework.stereotype.Service;

@Service
public class RaceStatsService {

    RaceStatsRepository repository;

    /*public RaceStats saveStats(RaceStatsDto raceStatsDto) {
        return repository.save(mapStatsFromDto(raceStatsDto));
    }*/

    public RaceStats mapStatsFromDto (RaceStatsDto statsOfRace) {
        RaceStats mappedStats = new RaceStats(statsOfRace.getBasicWs(), statsOfRace.getBasicBs(), statsOfRace.getBasicStr(),
                statsOfRace.getBasicTo(), statsOfRace.getBasicAg(), statsOfRace.getBasicInt(), statsOfRace.getBasicWp(),
                statsOfRace.getBasicFel(),statsOfRace.getMaxWounds(), statsOfRace.getMovement());
        return mappedStats;
    }
}
