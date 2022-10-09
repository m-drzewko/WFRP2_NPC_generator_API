package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final RaceStatsService statsService;

    public RaceService(RaceRepository raceRepository, RaceStatsService statsService) {
        this.raceRepository = raceRepository;
        this.statsService = statsService;
    }

    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    public Race saveNewRace(RaceStatsDto raceToSave) {
        return raceRepository.save(mapRaceFromDto(raceToSave));
    }

    public Race mapRaceFromDto(RaceStatsDto raceToSave) {
        RaceStats stats = statsService.mapStatsFromDto(raceToSave);
        return new Race(raceToSave.getName(), stats);
    }
}
