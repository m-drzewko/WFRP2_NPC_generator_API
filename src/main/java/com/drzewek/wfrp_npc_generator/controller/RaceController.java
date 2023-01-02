package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RaceController {

    private final RaceService service;

    public RaceController(RaceService service) {
        this.service = service;
    }

    @Operation(summary = "Returns all races")
    @ApiResponse(responseCode = "200",
        description = "Returned all races",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Race.class))) })
    @GetMapping("/races")
    public List<Race> getAllRaces() {
        return service.getAllRaces();
    }

    @Operation(summary = "Saves new race based on provided DTO")
    @ApiResponse(responseCode = "200",
        description = "Saved new race to repository",
        content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Race.class))})
    @PostMapping("/races/new")
    public Race submitRace(@RequestBody RaceWriteDto newRace) {
        return service.saveNewRace(newRace);
    }
}