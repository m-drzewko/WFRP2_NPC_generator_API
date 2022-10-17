package com.drzewek.wfrp_npc_generator.repository;

import com.drzewek.wfrp_npc_generator.model.Race;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class RaceRepositoryTest {

    @Autowired
    private RaceRepository raceRepository;

    @Test
    void shouldSaveRace() {
        Race race = new Race("testRace");

        var savedRace = raceRepository.save(race);

        assertNotNull(savedRace);
        assertEquals("testRace", savedRace.getName());
        assertEquals(1, raceRepository.findAll().size());
    }
}