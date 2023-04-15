package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.NpcDto;
import com.drzewek.wfrp_npc_generator.model.response.ResponseObject;
import com.drzewek.wfrp_npc_generator.service.NpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/npc")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class NpcController {

    private final NpcService npcService;

    @PostMapping("/generate")
    public ResponseObject<NpcDto> generateNpc(@RequestHeader (HttpHeaders.ACCEPT_LANGUAGE) String lang,
                                              @RequestParam(value = "race") String race,
                                              @RequestParam(value = "gender") String gender) {
        //TODO: change request type to GET
        return new ResponseObject<>(HttpStatus.ACCEPTED, "Returning generated NPC", npcService.generateNpc(lang, race, gender));
    }
}
