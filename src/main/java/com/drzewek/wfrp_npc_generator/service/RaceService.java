package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.RaceStatsDtoMapper;
import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    private final RaceStatsDtoMapper mapper;

    public RaceService(RaceRepository raceRepository, RaceStatsDtoMapper mapper) {
        this.raceRepository = raceRepository;
        this.mapper = mapper;
    }

    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    public Race saveNewRace(RaceWriteDto raceToSave) {
        return raceRepository.save(mapper.dtoToRace(raceToSave));
    }
}
