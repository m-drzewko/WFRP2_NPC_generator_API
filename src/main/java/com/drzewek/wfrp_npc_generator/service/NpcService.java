package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.Gender;
import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.repository.NpcRepository;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import com.drzewek.wfrp_npc_generator.utility.Generator;
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

    public Npc generateNpc(String race) {
        String raceToFind = race.toLowerCase();
        raceToFind = StringUtils.capitalize(raceToFind);
        Race raceToAssign = raceRepository.findByName(raceToFind);

        if (raceToAssign == null) {
            throw new EntityNotFoundException("Race " + race + " does not exist!");
        }

        Npc generatedNpc = new Npc();

        generatedNpc.setRace(raceToAssign);
        generatedNpc.setGender(Generator.generateGender());
        generatedNpc.setName("Test NPC");

        if (generatedNpc.getGender() == Gender.FEMALE) {
            if (generatedNpc.getRace().getName().equals("Dwarf")) {
                generatedNpc.setHeight(generatedNpc.getRace().getBaseHeight() + Generator.generate2d10() - 15);
            } else {
                generatedNpc.setHeight(generatedNpc.getRace().getBaseHeight() + Generator.generate2d10() - 10);
            }
        } else {
            generatedNpc.setHeight(generatedNpc.getRace().getBaseHeight() + Generator.generate2d10());
        }

        generatedNpc.setWeight(Generator.generateFromRange(
                generatedNpc.getRace().getMinimumWeight(),
                generatedNpc.getRace().getMaximumWeight()));
        generatedNpc.setAge(Generator.generateFromRange(
                generatedNpc.getRace().getMinimumAge(),
                generatedNpc.getRace().getMaximumAge()));
        generatedNpc.setHairColor(generatedNpc.getRace().
                getHairColors().get(Generator.generateD10() - 1));
        generatedNpc.setEyeColor(generatedNpc.getRace().
                getEyeColors().get(Generator.generateD10() - 1));

        HashMap<String, Integer> statistics = Generator.generateStatistics();

        generatedNpc.setWeaponSkill(generatedNpc.getRace().getStats().getBasicWeaponSkill()
                + statistics.get("weaponSkill"));
        generatedNpc.setBallisticSkill(generatedNpc.getRace().getStats().getBasicBallisticSkill() + statistics.get("ballisticSkill"));
        generatedNpc.setStrength(generatedNpc.getRace().getStats().getBasicStrength() + statistics.get("strength"));
        generatedNpc.setToughness(generatedNpc.getRace().getStats().getBasicToughness() + statistics.get("toughness"));
        generatedNpc.setAgility(generatedNpc.getRace().getStats().getBasicAgility() + statistics.get("agility"));
        generatedNpc.setIntelligence(generatedNpc.getRace().getStats().getBasicIntelligence() + statistics.get("intelligence"));
        generatedNpc.setWillPower(generatedNpc.getRace().getStats().getBasicWillPower() + statistics.get("willPower"));
        generatedNpc.setFellowship(generatedNpc.getRace().getStats().getBasicFellowship() + statistics.get("fellowship"));

        generatedNpc.setWounds(Generator.generateWounds(
                generatedNpc.getRace().getStats().getMaxWounds()));
        generatedNpc.setStrengthBonus(
                generatedNpc.getStrength() / 10);
        generatedNpc.setToughnessBonus(
                generatedNpc.getToughness() / 10);
        generatedNpc.setMovement(
                generatedNpc.getRace().getStats().getMovement());

        return generatedNpc;
    }
}
