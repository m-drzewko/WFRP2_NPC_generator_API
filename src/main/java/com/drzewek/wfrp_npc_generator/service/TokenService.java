package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.TokenType;
import com.drzewek.wfrp_npc_generator.model.entity.Token;
import com.drzewek.wfrp_npc_generator.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    private ResourceBundle applicationMessages = ResourceBundle.getBundle("ApplicationMessages");

    public Token findToken(String token) throws NoSuchElementException {

        if (!tokenRepository.existsByToken(token)) {
            throw new NoSuchElementException(applicationMessages.getString("error.token.no-such-token"));
        }

        return tokenRepository.findByToken(token);
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public boolean isExpired (Token token) {
        return token.getExpirationDate().getTime() <= new Date().getTime();
    }

    public boolean validateToken(Token token, TokenType type) throws SecurityException {
        if (isExpired(token)) {
            throw new SecurityException(applicationMessages.getString("error.token.expired"));
        } else if (token.getTokenType() != type) {
            throw new SecurityException(applicationMessages.getString("error.token.invalid-type"));
        } else if (token.isUsed()) {
            throw new SecurityException(applicationMessages.getString("error.token.used"));
        } else {
            return true;
        }
    }
}
