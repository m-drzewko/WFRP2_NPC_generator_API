package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceStatsWriteDto;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = RaceController.class)
class RaceControllerTest {

    @MockBean
    private RaceService raceService;

    @Autowired
    private RaceController raceController;

    private List<Race> testRaceList;

    private RaceWriteDto testRaceDto;

    private Race testRaceToSave;

    private Race testRaceToSave2;

    @BeforeEach
    void setup() {
        testRaceDto = new RaceWriteDto("testRaceDto",
                new RaceStatsWriteDto(10, 10,10,
                        10, 10, 10, 10,
                        10, 12, 4));

        testRaceToSave = new Race("testRace",
                new RaceStats(10, 10, 10,
                        10, 10, 10, 10,
                        10, 12, 4));

        testRaceToSave2 = new Race("testRace2",
                new RaceStats(15, 15, 15,
                        15, 15, 15, 15,
                        15, 11, 4));

        testRaceList = Stream.of(testRaceToSave, testRaceToSave2)
                .collect(Collectors.toList());
    }

    @Test
    void shouldReturnListOfRaces() {
        //given
        when(raceService.getAllRaces()).thenReturn(testRaceList);

        //when
        List<Race> races = raceController.getAllRaces();

        //then
        assertNotNull(races);
        assertEquals(2,races.size());
        assertEquals(11, races.get(1).getStats().getMaxWounds());
    }

    @Test
    void shouldSaveProvidedRaceDto() {
        //given
        when(raceService.saveNewRace(any(RaceWriteDto.class))).thenReturn(testRaceToSave);

        //when
        Race returnedRace = raceController.submitRace(testRaceDto);

        //then
        assertNotNull(returnedRace);
        assertEquals(10, returnedRace.getStats().getBasicFel());
        assertEquals("testRace", returnedRace.getName());
    }
}