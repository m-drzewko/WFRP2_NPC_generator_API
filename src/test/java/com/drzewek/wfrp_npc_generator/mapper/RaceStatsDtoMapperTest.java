package com.drzewek.wfrp_npc_generator.mapper;

import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.entity.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceStatsWriteDto;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RaceStatsDtoMapperTest {

    private RaceStatsDtoMapper mapper = Mappers.getMapper(RaceStatsDtoMapper.class);

    private Race race;

    private RaceStats stats;

    private RaceStatsWriteDto raceStatsDto;

    private RaceWriteDto raceDto;


    @BeforeEach
    void setUp() {
        stats = new RaceStats(15, 15, 15, 15,
                15, 15, 15,
                15, 12, 4);

        race = new Race("testName", stats,
                List.of("Dark Brown", "Black"),
                List.of("Purple", "Black"),
                30, 125, 170, 40, 95);

        raceStatsDto = new RaceStatsWriteDto(20, 20, 20,
                20, 20, 20, 20,
                20, 13, 5);

        raceDto = new RaceWriteDto("testDto", raceStatsDto, List.of("Dark Brown", "Black"),
                List.of("Purple", "Black"),
                30, 125, 170, 40, 95);
    }

    @Test
    void shouldMapStatsDtoToRaceStats() {
        //given & when
        RaceStats mappedStats = mapper.statsDtoToRaceStats(raceStatsDto);

        //then
        assertNotNull(mappedStats);
        assertEquals(13, mappedStats.getMaxWounds());
    }

    @Test
    void shouldMapRaceStatsToDto() {
        //given & when
        RaceStatsWriteDto mappedDto = mapper.raceStatsToDto(stats);

        //then
        assertNotNull(mappedDto);
        assertEquals(12, mappedDto.getMaxWounds());
    }

    @Test
    void shouldMapRaceDtoToRace() {
        //given & when
        Race mappedRace = mapper.dtoToRace(raceDto);

        //then
        assertNotNull(mappedRace);
        assertNotNull(mappedRace.getStats());
        assertEquals("testDto", mappedRace.getName());
    }

    @Test
    void shouldMapRaceToRaceDto() {
        //given & when
        RaceWriteDto mappedDto = mapper.raceToDto(race);

        //then
        assertNotNull(mappedDto);
        assertNotNull(mappedDto.getStats());
        assertEquals("testName", mappedDto.getName());
    }
}