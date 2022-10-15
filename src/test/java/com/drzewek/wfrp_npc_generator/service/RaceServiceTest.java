package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.RaceStatsDtoMapper;
import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceStatsWriteDto;
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
    private RaceStatsDtoMapper mapper;

    @InjectMocks
    private RaceService raceService;

    @Test
    void shouldSaveNewRaceFromDto() {
        //given
        RaceWriteDto testRaceDto = new RaceWriteDto("testRaceDto", new RaceStatsWriteDto(10, 10,
                10, 10, 10, 10, 10,
                10, 12, 4));

        Race testRace = new Race("testRace", new RaceStats(10, 10, 10,
                10, 10, 10, 10, 10,
                12, 4));

        when(mapper.dtoToRace(any(RaceWriteDto.class))).thenReturn(testRace);
        when(raceRepository.save(any(Race.class))).thenReturn(testRace);

        //when
        Race savedRace = raceService.saveNewRace(testRaceDto);

        //then
        assertNotNull(savedRace);
        assertEquals("testRace", savedRace.getName());
    }
}