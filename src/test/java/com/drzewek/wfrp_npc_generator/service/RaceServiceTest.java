package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {
    @InjectMocks
    private RaceService raceService;

    @Mock
    private RaceRepository raceRepository;
    @Mock
    private RaceStatsService raceStatsService;

    @Test
    void shouldSaveNewRace() {
        //given
        RaceStatsDto testRace = new RaceStatsDto("testRace", 10, 10,
                10, 10, 10, 10, 10,
                10, 12, 4);

        //when
        raceService.saveNewRace(testRace);
        //then
        assertEquals(1, raceRepository.findAll().size());
    }

}