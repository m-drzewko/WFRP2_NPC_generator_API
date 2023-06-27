package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.LoginDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private AuthenticationManager manager;

    private JwtService jwtService;

    public ResponseEntity<Void> authenticate(LoginDto loginDto, HttpServletResponse response) {
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        jwtService.setHttpHeaders(authenticate, response);
        return ResponseEntity.ok(null);
    }

    //TODO implement method to refresh expired JWT using generateJWTRefreshToken() from JwtService
}
