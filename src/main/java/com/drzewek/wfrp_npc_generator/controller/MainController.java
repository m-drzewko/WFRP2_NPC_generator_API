package com.drzewek.wfrp_npc_generator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String index() {
        return "Random NPC generator for WFRP II ed";
    }
}
