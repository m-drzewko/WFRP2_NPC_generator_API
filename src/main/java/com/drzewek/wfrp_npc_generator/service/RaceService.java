package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.RaceStatsDtoMapper;
import com.drzewek.wfrp_npc_generator.model.RaceDto;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class RaceService {

    private final RaceRepository raceRepository;

    private final RaceStatsDtoMapper mapper;

    public List<RaceDto> getAllRaces() {
        log.trace("Returning all races");
        List<RaceDto> listOfDtos = raceRepository.findAll().stream()
                .map(race -> mapper.raceToDto(race))
                .collect(Collectors.toList());
        return listOfDtos;
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

    public Race getRaceById(Long id) {
        return raceRepository.findById(id).orElseThrow();
    }

    public Race getRaceByName(String name) {
        return raceRepository.findByName(name).orElseThrow();
    }
}
