package com.drzewek.wfrp_npc_generator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Random NPC generator for WFRP II ed", HttpStatus.ACCEPTED);
    }

}
