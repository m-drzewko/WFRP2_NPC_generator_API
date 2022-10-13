package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import com.drzewek.wfrp_npc_generator.repository.RaceStatsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {

    @Mock
    private RaceRepository raceRepository;
    @Mock
    private RaceStatsService raceStatsService;

    @InjectMocks
    private RaceService raceService;

    @Test
    void shouldSaveNewRaceFromDto() {
        //given
        RaceStatsDto testRaceDto = new RaceStatsDto("testRaceDto", 10, 10,
                10, 10, 10, 10, 10,
                10, 12, 4);

        Race mappedRaceToSave = new Race("testRace", new RaceStats(10, 10, 10,
                10, 10, 10, 10, 10,
                12, 4));

        Mockito.when(raceRepository.save(any(Race.class))).thenReturn(mappedRaceToSave);

        //when
        Race savedRace = raceService.saveNewRace(testRaceDto);

        //then
        assertNotNull(savedRace);
        assertEquals("testRace", savedRace.getName());
    }

    @Test
    void shouldMapToRaceFromDto() {
        //given
        RaceStatsDto testRaceDto = new RaceStatsDto("testRaceDto", 10, 10,
                10, 10, 10, 10, 10,
                10, 12, 4);

        RaceStats testStats = new RaceStats(10, 10, 10,
                10, 10, 10, 10, 10,
                12, 4);

        Mockito.when(raceStatsService.mapStatsFromDto(any(RaceStatsDto.class))).thenReturn(testStats);

        //when
        Race mappedRace = raceService.mapRaceFromDto(testRaceDto);

        //then
        assertNotNull(mappedRace);
        assertEquals(4, mappedRace.getStats().getMovement());
    }
}