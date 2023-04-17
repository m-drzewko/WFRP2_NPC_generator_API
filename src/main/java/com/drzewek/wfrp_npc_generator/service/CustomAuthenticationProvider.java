package com.drzewek.wfrp_npc_generator.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    private PasswordEncoder encoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Attempting authentication: {}", authentication.toString());
        UserDetails userDetails = userService.loadUserByUsername(authentication.getName());
        if (encoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(),
                    authentication.getCredentials().toString(), userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Incorrect password!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
