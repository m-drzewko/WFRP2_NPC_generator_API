package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.RaceStatsDtoMapper;
import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RaceService {

    private final RaceRepository raceRepository;

    private final RaceStatsDtoMapper mapper;

    public RaceService(RaceRepository raceRepository, RaceStatsDtoMapper mapper) {
        this.raceRepository = raceRepository;
        this.mapper = mapper;
    }

    public List<Race> getAllRaces() {
        log.trace("Returning all races");
        return raceRepository.findAll();
    }

    public Race saveNewRace(RaceWriteDto raceToSave) {
        log.trace("Saving new race: " + raceToSave.getName());
        return raceRepository.save(mapper.dtoToRace(raceToSave));
    }
}
