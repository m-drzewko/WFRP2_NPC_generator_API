package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.Gender;
import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.repository.NpcRepository;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import com.drzewek.wfrp_npc_generator.utility.NpcUtility;
import com.drzewek.wfrp_npc_generator.utility.RaceUtility;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class NpcService {

    private final RaceRepository raceRepository;
    private final NpcRepository npcRepository;
    private static final String RANDOM = "random";
    private RaceUtility raceUtility;

    public Npc generateNpc(String lang, String raceName, String gender) {

        Npc generatedNpc;

        if (raceName.equals(RANDOM) && gender.equals(RANDOM)) {
            generatedNpc = NpcUtility.generateNpc(lang, null, raceUtility.generateRace());
        } else if (raceName.equals("random")) {
            if (gender.equals("male")) {
                generatedNpc = NpcUtility.generateNpc(lang, Gender.MALE, raceUtility.generateRace());
            } else if (gender.equals("female")) {
                generatedNpc = NpcUtility.generateNpc(lang, Gender.FEMALE, raceUtility.generateRace());
            } else throw new EntityNotFoundException("Gender " + gender + " does not exist!");
        } else if (gender.equals("random")) {
            String raceToFind = raceName.toLowerCase();
            raceToFind = StringUtils.capitalize(raceToFind);
            Race raceToAssign = raceRepository.findByName(raceToFind)
                    .orElseThrow(() -> new EntityNotFoundException("Race " + raceName + " does not exist!"));
            generatedNpc = NpcUtility.generateNpc(lang, null, raceToAssign);
        } else {
            String raceToFind = raceName.toLowerCase();
            raceToFind = StringUtils.capitalize(raceToFind);
            Race raceToAssign = raceRepository.findByName(raceToFind)
                    .orElseThrow(() -> new EntityNotFoundException("Race " + raceName + " does not exist!"));
            if (gender.equals("male")) {
                generatedNpc = NpcUtility.generateNpc(lang, Gender.MALE, raceToAssign);
            } else if (gender.equals("female")) {
                generatedNpc = NpcUtility.generateNpc(lang, Gender.FEMALE, raceToAssign);
            } else throw new EntityNotFoundException("Gender " + gender + " does not exist!");
        }

        return generatedNpc;
    }
}
