package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.entity.RaceStats;
import com.drzewek.wfrp_npc_generator.model.RaceStatsWriteDto;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

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

    private List<Race> raceList;

    private RaceWriteDto raceWriteDto;

    private Race raceToSave;

    private Race raceToSave2;

    @BeforeEach
    void setup() {
        raceWriteDto = new RaceWriteDto("testRaceDto",
                new RaceStatsWriteDto(10, 10,10,
                        10, 10, 10, 10,
                        10, 12, 4),
                new String[]{"Silver", "Ash Blond"}, new String[]{"Grey Blue", "Blue"},
                30, 125, 170, 40, 95);

        raceToSave = new Race("testRace",
                new RaceStats(10, 10, 10,
                        10, 10, 10, 10,
                        10, 12, 4),
                new String[]{"Corn", "Yellow"},
                new String[]{"Green", "Copper"},
                30, 125, 170, 40, 95);

        raceToSave2 = new Race("testRace2",
                new RaceStats(15, 15, 15,
                        15, 15, 15, 15,
                        15, 11, 4),
                new String[]{"Copper", "Light Brown"},
                new String[]{"Light Brown", "Brown"},
                30, 125, 170, 40, 95);

        raceList = Arrays.asList(raceToSave, raceToSave2);

    }

    @Test
    void shouldReturnListOfRaces() {
        //given
        when(raceService.getAllRaces()).thenReturn(raceList);

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
        when(raceService.saveNewRace(any(RaceWriteDto.class))).thenReturn(raceToSave);

        //when
        Race returnedRace = raceController.submitRace(raceWriteDto);

        //then
        assertNotNull(returnedRace);
        assertEquals(10, returnedRace.getStats().getBasicFellowship());
        assertEquals("testRace", returnedRace.getName());
    }
}