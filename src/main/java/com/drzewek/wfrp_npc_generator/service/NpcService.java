package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.Gender;
import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.repository.NpcRepository;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import com.drzewek.wfrp_npc_generator.utility.Generator;
import com.drzewek.wfrp_npc_generator.utility.NpcUtility;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;

@Service
public class NpcService {

    private final RaceRepository raceRepository;
    private final NpcRepository npcRepository;

    public NpcService(RaceRepository raceRepository,
                      NpcRepository npcRepository) {
        this.raceRepository = raceRepository;
        this.npcRepository = npcRepository;
    }

    public Npc generateNpc(String raceName) {
        String raceToFind = raceName.toLowerCase();
        raceToFind = StringUtils.capitalize(raceToFind);
        Race raceToAssign = raceRepository.findByName(raceToFind);

        if (raceToAssign == null) {
            throw new EntityNotFoundException("Race " + raceName + " does not exist!");
        }

        Npc generatedNpc = NpcUtility.generateNpc(raceToAssign);

        return generatedNpc;
    }
}
