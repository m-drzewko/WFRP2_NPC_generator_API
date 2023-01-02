package com.drzewek.wfrp_npc_generator.repository;


import com.drzewek.wfrp_npc_generator.model.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    Race findByName(String race);
}
