package com.drzewek.wfrp_npc_generator.mapper;

import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.entity.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RaceStatsDtoMapper {

    RaceStats statsDtoToRaceStats(RaceStatsDto dto);

    RaceStatsDto raceStatsToDto(RaceStats entity);

    @Mapping(target = "stats", source = "stats")
    Race dtoToRace(RaceDto dto);

    @Mapping(target = "stats", source = "stats")
    RaceDto raceToDto(Race entity);

}
