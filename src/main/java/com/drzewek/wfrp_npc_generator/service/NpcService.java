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

    private final NpcUtility npcUtility;

    public NpcService(RaceRepository raceRepository,
                      NpcRepository npcRepository, NpcUtility npcUtility) {
        this.raceRepository = raceRepository;
        this.npcRepository = npcRepository;
        this.npcUtility = npcUtility;
    }

    public Npc generateNpc(String raceName) {
        String raceToFind = raceName.toLowerCase();
        raceToFind = StringUtils.capitalize(raceToFind);
        Race raceToAssign = raceRepository.findByName(raceToFind);

        if (raceToAssign == null) {
            throw new EntityNotFoundException("Race " + raceName + " does not exist!");
        }

        Npc generatedNpc = npcUtility.generateNpc(raceToAssign);

        return generatedNpc;
    }
}
