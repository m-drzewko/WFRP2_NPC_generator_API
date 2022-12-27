package com.drzewek.wfrp_npc_generator.utility;

import com.drzewek.wfrp_npc_generator.model.RaceStatsWriteDto;
import com.drzewek.wfrp_npc_generator.model.RaceWriteDto;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import com.drzewek.wfrp_npc_generator.repository.RaceStatsRepository;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseUtility {

    private final RaceService raceService;

    public DatabaseUtility(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostConstruct
    public void initializeRaceDatabase() {

        RaceWriteDto elf = new RaceWriteDto("Elf", new RaceStatsWriteDto(20, 30,
                20, 20, 30, 20,
                20, 20, 12, 5),
                new String[]{"Silver", "Ash Blond", "Corn", "Yellow", "Copper",
                        "Light Brown", "Light Brown", "Brown", "Dark Brown", "Black"},
                new String[]{"Grey Blue", "Blue", "Green", "Copper", "Light Brown",
                        "Brown", "Dark Brown", "Silver", "Purple", "Black"},
                30, 125, 170, 40, 95);
        RaceWriteDto human = new RaceWriteDto("Human", new RaceStatsWriteDto(20, 20,
                20, 20, 20, 20,
                20, 20, 13, 4),
                new String[]{"Ash Blond", "Corn", "Yellow", "Copper", "Red",
                        "Light Brown", "Brown", "Brown", "Dark Brown", "Black"},
                new String[]{"Pale Grey", "Grey Blue", "Blue", "Green", "Copper",
                        "Light Brown", "Brown", "Dark Brown", "Purple", "Black"},
                16, 35, 160, 50, 110);
        RaceWriteDto dwarf = new RaceWriteDto("Dwarf", new RaceStatsWriteDto(30, 20,
                20, 30, 10, 20,
                20, 10, 14, 3),
                new String[]{"Ash Blond", "Yellow", "Red", "Copper","Light Brown",
                        "Brown", "Brown", "Dark Brown", "Blue Black", "Black"},
                new String[]{"Pale Grey", "Blue", "Copper", "Light Brown", "Light Brown",
                        "Brown", "Brown", "Dark Brown", "Dark Brown", "Purple"},
                0, 0, 0, 0, 0);
        RaceWriteDto halfling = new RaceWriteDto("Halfling", new RaceStatsWriteDto(10, 30,
                10, 10, 30, 20,
                20, 30, 11, 4),
                new String[]{"Ash Blond", "Corn", "Yellow", "Yellow", "Copper",
                        "Red", "Light Brown", "Brown", "Dark Brown", "Black"},
                new String[]{"Blue", "Hazel", "Light Brown", "Light Brown",
                        "Brown", "Brown", "Dark Brown", "Dark Brown", "Dark Brown"},
                0, 0, 0, 0, 0);

        raceService.saveNewRace(elf);
        raceService.saveNewRace(human);
        raceService.saveNewRace(dwarf);
        raceService.saveNewRace(halfling);
    }
}
