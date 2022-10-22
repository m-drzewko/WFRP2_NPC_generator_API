package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.RaceStatsDtoMapper;
import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceStatsWriteDto;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
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

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private RaceStatsDtoMapper mapper;

    @InjectMocks
    private RaceService raceService;

    private Race race;

    private RaceWriteDto raceDto;

    @BeforeEach
    void setup() {
        race = new Race("testRace", new RaceStats(10, 10, 10,
                10, 10, 10, 10, 10,
                12, 4));

        raceDto = new RaceWriteDto("testRaceDto", new RaceStatsWriteDto(10, 10,
                10, 10, 10, 10, 10,
                10, 12, 4));
    }

    @Test
    void shouldSaveNewRaceFromDto() {
        //given
        when(mapper.dtoToRace(any(RaceWriteDto.class))).thenReturn(race);
        when(raceRepository.save(any(Race.class))).thenReturn(race);

        //when
        Race savedRace = raceService.saveNewRace(raceDto);

        //then
        assertNotNull(savedRace);
        assertEquals("testRace", savedRace.getName());
    }
}