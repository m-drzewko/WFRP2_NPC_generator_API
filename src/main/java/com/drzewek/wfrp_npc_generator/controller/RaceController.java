package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.RaceDto;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.response.ResponseObject;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/race")
public class RaceController {

    private final RaceService service;

    @Operation(summary = "Returns all races")
    @ApiResponse(responseCode = "200",
        description = "Returned all races",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Race.class))) })
    @GetMapping("/races")
    public ResponseObject<List<RaceDto>> getAllRaces() {
        return new ResponseObject<>(HttpStatus.OK, "Returning all races", service.getAllRaces());
    }
}