package com.drzewek.wfrp_npc_generator.utility;

import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
@AllArgsConstructor
public class RaceUtility {

    private Random random;
    private RaceService raceService;

    public Race generateRace() {
        int number = random.ints(1, 5).findFirst().getAsInt();

        return raceService.getRaceById((long) number);
    }
}
