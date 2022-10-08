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

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RaceStatsService statsService;

    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    public Race saveNewRace(RaceStatsDto raceToSave) {
        RaceStats stats = statsService.mapStatsFromDto(raceToSave);
        Race newRace = new Race(raceToSave.getName(), stats);
        return raceRepository.save(newRace);
    }
}
