package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.TokenType;
import com.drzewek.wfrp_npc_generator.model.entity.Token;
import com.drzewek.wfrp_npc_generator.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TokenService {

    private TokenRepository tokenRepository;

    public Token findToken(String token) throws NoSuchElementException {

        if (!tokenRepository.existsByToken(token)) {
            throw new NoSuchElementException("Token does not exist!");
        }

        return tokenRepository.findByToken(token);
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public boolean isExpired (Token token) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        if (token.getExpirationDate().getTime() > new Date().getTime()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateToken(Token token, TokenType type) throws SecurityException {
        if (isExpired(token)) {
            throw new SecurityException("TOKEN EXPIRED!");
        } else if (token.getTokenType() != type) {
            throw new SecurityException("INVALID TOKEN TYPE!");
        } else if (token.isUsed()) {
            throw new SecurityException("TOKEN ALREADY USED!");
        } else {
            return true;
        }
    }
}
