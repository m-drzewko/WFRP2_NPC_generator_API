package com.drzewek.wfrp_npc_generator.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {

    public static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secret;

    private List<String> getClaimsFromUser(Authentication user) {
        return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    public String generateJWToken(Authentication user, Date date, Algorithm algorithm) {
        List<String> claimsFromUser = getClaimsFromUser(user);
        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(date)
                .withIssuer("WFRP")
                .withClaim("roles", claimsFromUser)
                .sign(algorithm);
    }
    public String generateJWTRefreshToken(Authentication user, Date date, Algorithm algorithm) {
        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000))
                .withIssuer("WFRP")
                .sign(algorithm);
    }

    public void setHttpHeaders(Authentication user, HttpServletResponse response) {
        Date date = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String accessToken = generateJWToken(user, date, algorithm);
        response.addHeader("Access_Token", accessToken);
    }

    public String decodeUsername(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        token = token.substring(7);
        DecodedJWT decodedToken = verifier.verify(token);
        return decodedToken.getSubject();
    }
}
