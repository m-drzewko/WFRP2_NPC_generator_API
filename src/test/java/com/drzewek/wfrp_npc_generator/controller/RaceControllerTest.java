package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class RaceControllerTest {

    @Mock
    private RaceService raceService;
    @InjectMocks
    private RaceController raceController;

    RaceStatsDto testRaceDto = new RaceStatsDto("testRaceDto", 10, 10,
            10, 10, 10, 10, 10,
            10, 12, 4);

    Race testRaceToSave = new Race("testRace", new RaceStats(10, 10, 10,
            10, 10, 10, 10, 10,
            12, 4));

    Race testRaceToSave2 = new Race("testRace2", new RaceStats(15, 15, 15,
            15, 15, 15, 15, 15,
            11, 4));
    List<Race> testArrayRaces = new ArrayList<>(2);

    @BeforeEach
    void setup() {
        testArrayRaces.add(0, testRaceToSave);
        testArrayRaces.add(1, testRaceToSave2);
    }

    @Test
    void shouldReturnListOfRaces() {
        //given
        Mockito.when(raceService.getAllRaces()).thenReturn(testArrayRaces);

        //when
        List<Race> returnedArray = raceController.getAllRaces();

        //then
        assertNotNull(returnedArray);
        assertEquals(2,returnedArray.size());
        assertEquals(11, returnedArray.get(1).getStats().getMaxWounds());
    }

    @Test
    void shouldSaveProvidedRaceDto() {
        //given
        Mockito.when(raceService.saveNewRace(any(RaceStatsDto.class))).thenReturn(testRaceToSave);

        //when
        Race returnedRace = raceController.submitRace(testRaceDto);

        //then
        assertNotNull(returnedRace);
        assertEquals(10, returnedRace.getStats().getBasicFel());
        assertEquals("testRace", returnedRace.getName());
    }

}