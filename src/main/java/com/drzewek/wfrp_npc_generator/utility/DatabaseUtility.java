package com.drzewek.wfrp_npc_generator.utility;

import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.entity.RaceStats;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseUtility {

    private final RaceService raceService;

    @PostConstruct
    public void initializeRaceDatabase() {

        Race elf = Race.builder()
                .name("Elf")
                .stats(RaceStats.builder()
                        .basicWeaponSkill(20)
                        .basicBallisticSkill(30)
                        .basicStrength(20)
                        .basicToughness(20)
                        .basicAgility(30)
                        .basicIntelligence(20)
                        .basicWillPower(20)
                        .basicFellowship(20)
                        .maxWounds(12)
                        .movement(5)
                        .build())
                .hairColors(List.of("Silver", "Ash Blond", "Corn", "Yellow", "Copper",
                        "Light Brown", "Light Brown", "Brown", "Dark Brown", "Black"))
                .hairColorsPl(List.of("Srebrny", "Popielaty", "Jasny blond", "Ciemny blond", "Rudy",
                        "Jasnobrązowy", "Jasnobrązowy", "Brązowy", "Ciemnobrązowy", "Czarny"))
                .eyeColors(List.of("Grey Blue", "Blue", "Green", "Copper", "Light Brown",
                        "Brown", "Dark Brown", "Silver", "Purple", "Black"))
                .eyeColorsPl(List.of("Ciemnoniebieski", "Niebieski", "Zielony", "Rudy", "Jasnobrązowy",
                        "Brązowy", "Ciemnobrązowy", "Srebrny", "Fioletowy", "Czarny"))
                .minimumAge(30)
                .maximumAge(125)
                .baseHeight(170)
                .minimumWeight(40)
                .maximumWeight(95)
                .build();

        Race human = Race.builder()
                .name("Human")
                .stats(RaceStats.builder()
                        .basicWeaponSkill(20)
                        .basicBallisticSkill(20)
                        .basicStrength(20)
                        .basicToughness(20)
                        .basicAgility(20)
                        .basicIntelligence(20)
                        .basicWillPower(20)
                        .basicFellowship(20)
                        .maxWounds(13)
                        .movement(4)
                        .build())
                .hairColors(List.of("Ash Blond", "Corn", "Yellow", "Copper", "Red",
                        "Light Brown", "Brown", "Brown", "Dark Brown", "Black"))
                .hairColorsPl(List.of("Popielaty", "Jasny blond", "Ciemny blond", "Rudy", "Ciemnorudy",
                        "Jasnobrązowy", "Brązowy", "Brązowy", "Ciemnobrązowy", "Czarny"))
                .eyeColors(List.of("Pale Grey", "Grey Blue", "Blue", "Green", "Copper",
                        "Light Brown", "Brown", "Dark Brown", "Purple", "Black"))
                .eyeColorsPl(List.of("Szary", "Ciemnoniebieski", "Niebieski", "Zielony", "Rudy",
                        "Jasnobrązowy", "Brązowy", "Ciemnobrązowy", "Fioletowy", "Czarny"))
                .minimumAge(16)
                .maximumAge(35)
                .baseHeight(160)
                .minimumWeight(50)
                .maximumWeight(110)
                .build();

        Race dwarf = Race.builder()
                .name("Dwarf")
                .stats(RaceStats.builder()
                        .basicWeaponSkill(30)
                        .basicBallisticSkill(20)
                        .basicStrength(20)
                        .basicToughness(30)
                        .basicAgility(10)
                        .basicIntelligence(20)
                        .basicWillPower(20)
                        .basicFellowship(10)
                        .maxWounds(14)
                        .movement(3)
                        .build())
                .hairColors(List.of("Ash Blond", "Yellow", "Red", "Copper","Light Brown",
                        "Brown", "Brown", "Dark Brown", "Blue Black", "Black"))
                .hairColorsPl(List.of("Popielaty", "Ciemny blond", "Ciemnorudy", "Rudy", "Jasnobrązowy",
                        "Brązowy", "Brązowy", "Ciemnobrązowy", "Kruczoczarny", "Czarny"))
                .eyeColors(List.of("Pale Grey", "Blue", "Copper", "Light Brown", "Light Brown",
                        "Brown", "Brown", "Dark Brown", "Dark Brown", "Purple"))
                .eyeColorsPl(List.of("Szary", "Niebieski", "Rudy", "Jasnobrązowy", "Jasnobrązowy",
                        "Brązowy", "Brązowy", "Ciemnobrązowy", "Ciemnobrązowy", "Fioletowy"))
                .minimumAge(20)
                .maximumAge(115)
                .baseHeight(145)
                .minimumWeight(45)
                .maximumWeight(100)
                .build();

        Race halfling = Race.builder()
                .name("Halfling")
                .stats(RaceStats.builder()
                        .basicWeaponSkill(10)
                        .basicBallisticSkill(30)
                        .basicStrength(10)
                        .basicToughness(10)
                        .basicAgility(30)
                        .basicIntelligence(20)
                        .basicWillPower(20)
                        .basicFellowship(30)
                        .maxWounds(11)
                        .movement(4)
                        .build())
                .hairColors(List.of("Ash Blond", "Corn", "Yellow", "Yellow", "Copper",
                        "Red", "Light Brown", "Brown", "Dark Brown", "Black"))
                .hairColorsPl(List.of("Popielaty", "Jasny blond", "Ciemny blond", "Ciemny blond", "Rudy",
                        "Ciemnorudy", "Jasnobrązowy", "Brązowy", "Ciemnobrązowy", "Czarny"))
                .eyeColors(List.of("Blue", "Hazel", "Light Brown", "Light Brown",
                        "Brown", "Brown", "Dark Brown", "Dark Brown", "Dark Brown"))
                .eyeColorsPl(List.of("Niebieski", "Kasztanowy", "Jasnobrązowy", "Jasnobrązowy", "Brązowy",
                        "Brązowy", "Brązowy", "Ciemnobrązowy", "Ciemnobrązowy", "Ciemnobrązowy"))
                .minimumAge(20)
                .maximumAge(60)
                .baseHeight(110)
                .minimumWeight(35)
                .maximumWeight(70)
                .build();

        raceService.saveNewRace(elf);
        raceService.saveNewRace(human);
        raceService.saveNewRace(dwarf);
        raceService.saveNewRace(halfling);
    }
}
