package com.drzewek.wfrp_npc_generator.mapper;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceStatsWriteDto;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract interface RaceStatsDtoMapper {

    RaceStats statsDtoToRaceStats(RaceStatsWriteDto dto);

    RaceStatsWriteDto raceStatsToDto(RaceStats entity);

    @Mapping(target = "stats", source = "statsDto")
    Race dtoToRace(RaceWriteDto dto);

    @Mapping(target = "statsDto", source = "stats")
    RaceWriteDto raceToDto(Race entity);

}
