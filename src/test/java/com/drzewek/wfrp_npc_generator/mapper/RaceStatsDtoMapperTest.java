package com.drzewek.wfrp_npc_generator.mapper;

import com.drzewek.wfrp_npc_generator.model.RaceDto;
import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.entity.RaceStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RaceStatsDtoMapperTest {

    private RaceStatsDtoMapper mapper = Mappers.getMapper(RaceStatsDtoMapper.class);

    private Race race;

    private RaceStats stats;

    private RaceStatsDto raceStatsDto;

    private RaceDto raceDto;


    @BeforeEach
    void setUp() {
        stats = RaceStats.builder()
                .basicWeaponSkill(15)
                .basicBallisticSkill(15)
                .basicStrength(15)
                .basicToughness(15)
                .basicAgility(15)
                .basicIntelligence(15)
                .basicWillPower(15)
                .basicFellowship(15)
                .maxWounds(12)
                .movement(4)
                .build();

        race = Race.builder()
                .name("testName")
                .stats(stats)
                .hairColors(List.of("Dark Brown", "Black"))
                .eyeColors(List.of("Purple", "Black"))
                .minimumAge(30)
                .maximumAge(125)
                .baseHeight(170)
                .minimumWeight(40)
                .maximumWeight(95)
                .build();

        raceStatsDto = RaceStatsDto.builder()
                .basicWeaponSkill(20)
                .basicBallisticSkill(20)
                .basicStrength(20)
                .basicToughness(20)
                .basicAgility(20)
                .basicIntelligence(20)
                .basicWillPower(20)
                .basicFellowship(20)
                .maxWounds(13)
                .movement(5)
                .build();

        raceDto = RaceDto.builder()
                .name("testDto")
                .stats(raceStatsDto)
                .hairColors(List.of("Dark Brown", "Black"))
                .eyeColors(List.of("Purple", "Black"))
                .minimumAge(30)
                .maximumAge(125)
                .baseHeight(170)
                .minimumWeight(40)
                .maximumWeight(95)
                .build();
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
        RaceStatsDto mappedDto = mapper.raceStatsToDto(stats);

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
        RaceDto mappedDto = mapper.raceToDto(race);

        //then
        assertNotNull(mappedDto);
        assertNotNull(mappedDto.getStats());
        assertEquals("testName", mappedDto.getName());
    }
}