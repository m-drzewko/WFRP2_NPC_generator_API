package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceStatsWriteDto;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = RaceController.class)
class RaceControllerTest {

    @MockBean
    private RaceService raceService;

    @Autowired
    private RaceController raceController;

    List<Race> testRaceList = new ArrayList<>();

    RaceWriteDto testRaceDto;

    Race testRaceToSave;

    Race testRaceToSave2;

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
        testRaceList.add(0, testRaceToSave);
        testRaceList.add(1, testRaceToSave2);
    }

    @Test
    void shouldReturnListOfRaces() {
        //given
        Mockito.when(raceService.getAllRaces()).thenReturn(testRaceList);

        //when
        List<Race> returnedList = raceController.getAllRaces();

        //then
        assertNotNull(returnedList);
        assertEquals(2,returnedList.size());
        assertEquals(11, returnedList.get(1).getStats().getMaxWounds());
    }

    @Test
    void shouldSaveProvidedRaceDto() {
        //given
        Mockito.when(raceService.saveNewRace(any(RaceWriteDto.class))).thenReturn(testRaceToSave);

        //when
        Race returnedRace = raceController.submitRace(testRaceDto);

        //then
        assertNotNull(returnedRace);
        assertEquals(10, returnedRace.getStats().getBasicFel());
        assertEquals("testRace", returnedRace.getName());
    }
}