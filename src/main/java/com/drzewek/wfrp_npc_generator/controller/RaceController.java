package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RaceController {

    @Autowired
    private RaceRepository raceRepository;

    @GetMapping("/races")
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    @PostMapping("/newrace")
    public Race submitRace(@RequestBody Race test) {
        return raceRepository.save(test);
    }
}
