package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.RaceDto;
import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.entity.RaceStats;
import com.drzewek.wfrp_npc_generator.model.response.ResponseObject;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

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

    private RaceDto raceDto;

    private Race raceToSave;

    private Race raceToSave2;

    @BeforeEach
    void setup() {
        raceDto = new RaceDto("testRaceDto",
                new RaceStatsDto(10, 10,10,
                        10, 10, 10, 10,
                        10, 12, 4),
                List.of("Silver", "Ash Blond"), List.of("Grey Blue", "Blue"),
                30, 125, 170, 40, 95);

        raceToSave = new Race("testRace",
                new RaceStats(10, 10, 10,
                        10, 10, 10, 10,
                        10, 12, 4),
                List.of("Corn", "Yellow"),
                List.of("Green", "Copper"),
                30, 125, 170, 40, 95);

        raceToSave2 = new Race("testRace2",
                new RaceStats(15, 15, 15,
                        15, 15, 15, 15,
                        15, 11, 4),
                List.of("Copper", "Light Brown"),
                List.of("Light Brown", "Brown"),
                30, 125, 170, 40, 95);

        raceList = Arrays.asList(raceToSave, raceToSave2);

    }

    @Test
    void shouldReturnResponseObjectWithListOfRaces() {
        //given
        when(raceService.getAllRaces()).thenReturn(raceList);

        //when
        ResponseObject<List<Race>> races = new ResponseObject<>(HttpStatus.OK, "Returning all races", raceService.getAllRaces());

        //then
        assertNotNull(races);
        assertEquals(2,races.getObject().size());
        assertEquals(11, races.getObject().get(1).getStats().getMaxWounds());
    }

    @Test
    void shouldSaveProvidedRaceDto() {
        //given
        when(raceService.saveNewRace(any(RaceDto.class))).thenReturn(raceToSave);

        //when
        ResponseObject<Race> returnedRace = new ResponseObject<>(HttpStatus.CREATED, "Saving new race", raceService.saveNewRace(raceDto));

        //then
        assertNotNull(returnedRace);
        assertEquals(10, returnedRace.getObject().getStats().getBasicFellowship());
        assertEquals("testRace", returnedRace.getObject().getName());
    }
}