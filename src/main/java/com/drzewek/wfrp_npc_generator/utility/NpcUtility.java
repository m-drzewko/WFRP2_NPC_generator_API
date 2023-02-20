package com.drzewek.wfrp_npc_generator.utility;

import com.drzewek.wfrp_npc_generator.model.Gender;
import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NpcUtility {

    public static Npc generateNpc(String lang, Gender gender, Race race) {
        Npc npc = new Npc();

        npc.setRace(race);

        if (gender==null) {
            npc.setGender(Generator.generateGender());
        } else {
            npc.setGender(gender);
        }

        if (npc.getGender() == Gender.FEMALE) {
            if (npc.getRace().getName().equals("Dwarf")) {
                npc.setHeight(npc.getRace().getBaseHeight() + Generator.generate2d10() - 15);
            } else {
                npc.setHeight(npc.getRace().getBaseHeight() + Generator.generate2d10() - 10);
            }
        } else {
            npc.setHeight(npc.getRace().getBaseHeight() + Generator.generate2d10());
        }

        npc.setWeight(Generator.generateFromRange(
                npc.getRace().getMinimumWeight(),
                npc.getRace().getMaximumWeight()));
        npc.setAge(Generator.generateFromRange(
                npc.getRace().getMinimumAge(),
                npc.getRace().getMaximumAge()));

        if (lang.equals("pl")) {
            npc.setHairColor(npc.getRace().
                    getHairColorsPl().get(Generator.generateD10() - 1));
            npc.setEyeColor(npc.getRace().
                    getEyeColorsPl().get(Generator.generateD10() - 1));
        } else {
            npc.setHairColor(npc.getRace().
                    getHairColors().get(Generator.generateD10() - 1));
            npc.setEyeColor(npc.getRace().
                    getEyeColors().get(Generator.generateD10() - 1));
        }

        HashMap<String, Integer> statistics = (HashMap<String, Integer>) Generator.generateStatistics();

        npc.setWeaponSkill(npc.getRace().getStats().getBasicWeaponSkill()
                + statistics.get("weaponSkill"));
        npc.setBallisticSkill(npc.getRace().getStats().getBasicBallisticSkill() + statistics.get("ballisticSkill"));
        npc.setStrength(npc.getRace().getStats().getBasicStrength() + statistics.get("strength"));
        npc.setToughness(npc.getRace().getStats().getBasicToughness() + statistics.get("toughness"));
        npc.setAgility(npc.getRace().getStats().getBasicAgility() + statistics.get("agility"));
        npc.setIntelligence(npc.getRace().getStats().getBasicIntelligence() + statistics.get("intelligence"));
        npc.setWillPower(npc.getRace().getStats().getBasicWillPower() + statistics.get("willPower"));
        npc.setFellowship(npc.getRace().getStats().getBasicFellowship() + statistics.get("fellowship"));

        npc.setWounds(Generator.generateWounds(
                npc.getRace().getStats().getMaxWounds()));
        npc.setStrengthBonus(
                npc.getStrength() / 10);
        npc.setToughnessBonus(
                npc.getToughness() / 10);
        npc.setMovement(
                npc.getRace().getStats().getMovement());

        return npc;
    }
}
