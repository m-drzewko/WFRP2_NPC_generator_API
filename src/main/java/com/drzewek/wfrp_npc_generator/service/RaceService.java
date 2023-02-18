package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.RaceStatsDtoMapper;
import com.drzewek.wfrp_npc_generator.model.RaceDto;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
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

    public Race saveNewRaceFromDto(RaceDto raceToSave) {
        if (raceRepository.existsByName(raceToSave.getName())) {
            log.info("Race " + raceToSave.getName() + " already exists! Only one race can exist with this name.");
            return raceRepository.findByName(raceToSave.getName()).get();
        } else {
            log.trace("Saving new race: " + raceToSave.getName());
            return raceRepository.save(mapper.dtoToRace(raceToSave));
        }
    }

    public Race saveNewRace(Race race) {
        if (raceRepository.existsByName(race.getName())) {
            log.info("Race " + race.getName() + " already exists! Only one race can exist with this name.");
            return raceRepository.findByName(race.getName()).get();
        } else {
            log.trace("Saving new race: " + race.getName());
            return raceRepository.save(race);
        }
    }
}
