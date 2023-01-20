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
        race = new Race("testRace", new RaceStats(10, 10, 10,
                10, 10, 10, 10, 10,
                12, 4), List.of("Silver", "Ash Blond", "Corn", "Yellow", "Copper",
                "Light Brown", "Light Brown", "Brown", "Dark Brown", "Black"),
                List.of("Grey Blue", "Blue", "Green", "Copper", "Light Brown",
                        "Brown", "Dark Brown", "Silver", "Purple", "Black"),
                30, 125, 170, 40, 95);

        raceDto = new RaceDto("testRaceDto", new RaceStatsDto(10, 10,
                10, 10, 10, 10, 10,
                10, 12, 4), List.of("Silver", "Ash Blond", "Corn", "Yellow", "Copper",
                "Light Brown", "Light Brown", "Brown", "Dark Brown", "Black"),
                List.of("Grey Blue", "Blue", "Green", "Copper", "Light Brown",
                        "Brown", "Dark Brown", "Silver", "Purple", "Black"),
                30, 125, 170, 40, 95);
    }

    @Test
    void shouldSaveNewRaceFromDto() {
        //given
        when(mapper.dtoToRace(any(RaceDto.class))).thenReturn(race);
        when(raceRepository.save(any(Race.class))).thenReturn(race);

        //when
        Race savedRace = raceService.saveNewRace(raceDto);

        //then
        assertNotNull(savedRace);
        assertEquals("testRace", savedRace.getName());
    }
}