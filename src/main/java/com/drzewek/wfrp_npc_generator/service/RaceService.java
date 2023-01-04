package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.RaceStatsDtoMapper;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RaceService {

    private final RaceRepository raceRepository;

    private final RaceStatsDtoMapper mapper;

    public List<Race> getAllRaces() {
        log.trace("Returning all races");
        return raceRepository.findAll();
    }

    public Race saveNewRace(RaceWriteDto raceToSave) {
        log.trace("Saving new race: " + raceToSave.getName());
        return raceRepository.save(mapper.dtoToRace(raceToSave));
    }
}
