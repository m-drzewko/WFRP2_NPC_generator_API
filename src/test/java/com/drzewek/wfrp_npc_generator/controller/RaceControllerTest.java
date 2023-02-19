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

        raceDto = RaceDto.builder()
                .name("testRaceDto")
                .stats(RaceStatsDto.builder()
                        .basicWeaponSkill(10)
                        .basicBallisticSkill(10)
                        .basicStrength(10)
                        .basicToughness(10)
                        .basicAgility(10)
                        .basicIntelligence(10)
                        .basicWillPower(10)
                        .basicFellowship(10)
                        .maxWounds(12)
                        .movement(4)
                        .build())
                .hairColors(List.of("Silver", "Ash Blond"))
                .eyeColors(List.of("Grey Blue", "Blue"))
                .minimumAge(30)
                .maximumAge(125)
                .baseHeight(170)
                .minimumWeight(40)
                .maximumWeight(95)
                .build();

        raceToSave = Race.builder()
                .name("testRace")
                .stats(RaceStats.builder()
                        .basicWeaponSkill(10)
                        .basicBallisticSkill(10)
                        .basicStrength(10)
                        .basicToughness(10)
                        .basicAgility(10)
                        .basicIntelligence(10)
                        .basicWillPower(10)
                        .basicFellowship(10)
                        .maxWounds(12)
                        .movement(4)
                        .build())
                .hairColors(List.of("Corn", "Yellow"))
                .eyeColors(List.of("Green", "Copper"))
                .minimumAge(30)
                .maximumAge(125)
                .baseHeight(170)
                .minimumWeight(40)
                .maximumWeight(95)
                .build();

        raceToSave2 = Race.builder()
                .name("testRace")
                .stats(RaceStats.builder()
                        .basicWeaponSkill(15)
                        .basicBallisticSkill(15)
                        .basicStrength(15)
                        .basicToughness(15)
                        .basicAgility(15)
                        .basicIntelligence(15)
                        .basicWillPower(15)
                        .basicFellowship(15)
                        .maxWounds(11)
                        .movement(4)
                        .build())
                .hairColors(List.of("Copper", "Light Brown"))
                .eyeColors(List.of("Light Brown", "Brown"))
                .minimumAge(30)
                .maximumAge(125)
                .baseHeight(170)
                .minimumWeight(40)
                .maximumWeight(95)
                .build();

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
        when(raceService.saveNewRaceFromDto(any(RaceDto.class))).thenReturn(raceToSave);

        //when
        ResponseObject<Race> returnedRace = new ResponseObject<>(HttpStatus.CREATED, "Saving new race", raceService.saveNewRaceFromDto(raceDto));

        //then
        assertNotNull(returnedRace);
        assertEquals(10, returnedRace.getObject().getStats().getBasicFellowship());
        assertEquals("testRace", returnedRace.getObject().getName());
    }
}