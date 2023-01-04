package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import com.drzewek.wfrp_npc_generator.service.NpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/npc")
@RequiredArgsConstructor
public class NpcController {

    private final NpcService npcService;

    @PostMapping("/generate")
    public Npc generateNpc(@RequestParam("race") String race) {

        return npcService.generateNpc(race);
    }
}
