package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceStatsDto;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RaceController {

    @Autowired
    private RaceService service;

    @GetMapping("/races")
    public List<Race> getAllRaces() {
        return service.getAllRaces();
    }

    @PostMapping("/newrace")
    public Race submitRace(@RequestBody RaceStatsDto newRace) {
        return service.saveNewRace(newRace);
    }
}