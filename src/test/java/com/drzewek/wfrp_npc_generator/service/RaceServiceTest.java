package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        RaceWriteDto testRaceDto = new RaceWriteDto("testRaceDto", 10, 10,
                10, 10, 10, 10, 10,
                10, 12, 4);

        Race testRace = new Race("testRace", new RaceStats(10, 10, 10,
                10, 10, 10, 10, 10,
                12, 4));

        when(raceRepository.save(any(Race.class))).thenReturn(testRace);

        //when
        Race savedRace = raceService.saveNewRace(testRaceDto);

        //then
        assertNotNull(savedRace);
        assertEquals("testRace", savedRace.getName());
    }

    @Test
    void shouldMapToRaceFromDto() {
        //given
        RaceWriteDto testRaceDto = new RaceWriteDto("testRaceDto", 10, 10,
                10, 10, 10, 10, 10,
                10, 12, 4);

        RaceStats testStats = new RaceStats(10, 10, 10,
                10, 10, 10, 10, 10,
                12, 4);

        when(raceStatsService.mapStatsFromDto(any(RaceWriteDto.class))).thenReturn(testStats);

        //when
        Race mappedRace = raceService.mapRaceFromDto(testRaceDto);

        //then
        assertNotNull(mappedRace);
        assertEquals(4, mappedRace.getStats().getMovement());
    }
}