package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.RaceStatsDtoMapper;
import com.drzewek.wfrp_npc_generator.model.RaceDto;
import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.entity.RaceStats;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private RaceStatsDtoMapper mapper;

    @InjectMocks
    private RaceService raceService;

    private Race race;

    private RaceDto raceDto;

    @BeforeEach
    void setup() {

        race = Race.builder()
                .name("testRace")
                .stats(RaceStats.builder()
                        .basicWeaponSkill(10)
                        .basicBallisticSkill(10)
                        .basicStrength(10)
                        .basicToughness(10)
                        .basicAgility(10)
                        .basicIntelligence(10)
                        .basicWillPower(10)
                        .basicFellowship(10)
                        .maxWounds(12)
                        .movement(4)
                        .build())
                .hairColors(List.of("Silver", "Ash Blond", "Corn", "Yellow", "Copper",
                        "Light Brown", "Light Brown", "Brown", "Dark Brown", "Black"))
                .eyeColors(List.of("Grey Blue", "Blue", "Green", "Copper", "Light Brown",
                        "Brown", "Dark Brown", "Silver", "Purple", "Black"))
                .minimumAge(30)
                .maximumAge(125)
                .baseHeight(170)
                .minimumWeight(40)
                .maximumWeight(95)
                .build();

        raceDto = RaceDto.builder()
                .name("testRaceDto")
                .stats(RaceStatsDto.builder()
                        .basicWeaponSkill(10)
                        .basicBallisticSkill(10)
                        .basicStrength(10)
                        .basicToughness(10)
                        .basicAgility(10)
                        .basicIntelligence(10)
                        .basicWillPower(10)
                        .basicFellowship(10)
                        .maxWounds(12)
                        .movement(4)
                        .build())
                .hairColors(List.of("Silver", "Ash Blond", "Corn", "Yellow", "Copper",
                        "Light Brown", "Light Brown", "Brown", "Dark Brown", "Black"))
                .eyeColors(List.of("Grey Blue", "Blue", "Green", "Copper", "Light Brown",
                        "Brown", "Dark Brown", "Silver", "Purple", "Black"))
                .minimumAge(30)
                .maximumAge(125)
                .baseHeight(170)
                .minimumWeight(40)
                .maximumWeight(95)
                .build();
    }

    @Test
    void shouldSaveNewRaceFromDto() {
        //given
        when(mapper.dtoToRace(any(RaceDto.class))).thenReturn(race);
        when(raceRepository.save(any(Race.class))).thenReturn(race);

        //when
        Race savedRace = raceService.saveNewRaceFromDto(raceDto);

        //then
        assertNotNull(savedRace);
        assertEquals("testRace", savedRace.getName());
    }
}