package com.drzewek.wfrp_npc_generator.repository;

import com.drzewek.wfrp_npc_generator.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameRepository extends JpaRepository<Name,Long> {
}
