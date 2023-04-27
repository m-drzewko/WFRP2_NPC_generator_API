package com.drzewek.wfrp_npc_generator.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
public class AuthorizationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secret = "coś";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/auth/login")) {
            filterChain.doFilter(request, response);
        } else {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header != null && header.startsWith("Bearer ")) {
                try {
                    String token = header.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256(secret);
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedToken = verifier.verify(token);
                    String username = decodedToken.getSubject();
                    String[] rolesArray = decodedToken.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
                    Arrays.stream(rolesArray).forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r)));
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setHeader("Error", e.getMessage());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
