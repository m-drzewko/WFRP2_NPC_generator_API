package com.drzewek.wfrp_npc_generator.utility;

import com.drzewek.wfrp_npc_generator.model.RaceStatsWriteDto;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseUtility {

    private final RaceService raceService;

    @PostConstruct
    public void initializeRaceDatabase() {

        RaceWriteDto elf = new RaceWriteDto("Elf", new RaceStatsWriteDto(20, 30,
                20, 20, 30, 20,
                20, 20, 12, 5),
                List.of("Silver", "Ash Blond", "Corn", "Yellow", "Copper",
                        "Light Brown", "Light Brown", "Brown", "Dark Brown", "Black"),
                List.of("Grey Blue", "Blue", "Green", "Copper", "Light Brown",
                        "Brown", "Dark Brown", "Silver", "Purple", "Black"),
                30, 125, 170, 40, 95);
        RaceWriteDto human = new RaceWriteDto("Human", new RaceStatsWriteDto(20, 20,
                20, 20, 20, 20,
                20, 20, 13, 4),
                List.of("Ash Blond", "Corn", "Yellow", "Copper", "Red",
                        "Light Brown", "Brown", "Brown", "Dark Brown", "Black"),
                List.of("Pale Grey", "Grey Blue", "Blue", "Green", "Copper",
                        "Light Brown", "Brown", "Dark Brown", "Purple", "Black"),
                16, 35, 160, 50, 110);
        RaceWriteDto dwarf = new RaceWriteDto("Dwarf", new RaceStatsWriteDto(30, 20,
                20, 30, 10, 20,
                20, 10, 14, 3),
                List.of("Ash Blond", "Yellow", "Red", "Copper","Light Brown",
                        "Brown", "Brown", "Dark Brown", "Blue Black", "Black"),
                List.of("Pale Grey", "Blue", "Copper", "Light Brown", "Light Brown",
                        "Brown", "Brown", "Dark Brown", "Dark Brown", "Purple"),
                20, 115, 145, 45, 100);
        RaceWriteDto halfling = new RaceWriteDto("Halfling", new RaceStatsWriteDto(10, 30,
                10, 10, 30, 20,
                20, 30, 11, 4),
                List.of("Ash Blond", "Corn", "Yellow", "Yellow", "Copper",
                        "Red", "Light Brown", "Brown", "Dark Brown", "Black"),
                List.of("Blue", "Hazel", "Light Brown", "Light Brown",
                        "Brown", "Brown", "Dark Brown", "Dark Brown", "Dark Brown"),
                20, 60, 110, 35, 70);

        raceService.saveNewRace(elf);
        raceService.saveNewRace(human);
        raceService.saveNewRace(dwarf);
        raceService.saveNewRace(halfling);
    }
}
