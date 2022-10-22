package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.Race;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RaceController {

    private final RaceService service;

    public RaceController(RaceService service) {
        this.service = service;
    }

    @GetMapping("/races")
    public List<Race> getAllRaces() {
        return service.getAllRaces();
    }

    @PostMapping("/races/new")
    public Race submitRace(@RequestBody RaceWriteDto newRace) {
        return service.saveNewRace(newRace);
    }
}