package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.LoginDto;
import com.drzewek.wfrp_npc_generator.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        return service.authenticate(loginDto, response);
    }

    //TODO add endpoint to refresh JWT when it's expired
}
