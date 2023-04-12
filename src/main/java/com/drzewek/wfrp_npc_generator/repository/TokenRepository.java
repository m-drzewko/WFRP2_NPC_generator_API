package com.drzewek.wfrp_npc_generator.repository;

import com.drzewek.wfrp_npc_generator.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByToken(String token);

    Token findByToken(String token);
}
