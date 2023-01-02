package com.drzewek.wfrp_npc_generator.repository;

import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NpcRepository extends JpaRepository<Npc, Long> {
}
