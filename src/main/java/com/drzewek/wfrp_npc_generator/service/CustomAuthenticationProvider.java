package com.drzewek.wfrp_npc_generator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private final PasswordEncoder encoder;

    private ResourceBundle applicationMessages = ResourceBundle.getBundle("ApplicationMessages");

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Attempting authentication: {}", authentication.toString());
        UserDetails userDetails = userService.loadUserByUsername(authentication.getName());
        if (encoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(),
                    authentication.getCredentials().toString(), userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException(applicationMessages.getString("error.password.incorrect-password"));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
