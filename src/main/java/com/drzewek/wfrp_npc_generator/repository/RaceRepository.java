package com.drzewek.wfrp_npc_generator.repository;


import com.drzewek.wfrp_npc_generator.model.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    Optional<Race> findByName(String race);
}
