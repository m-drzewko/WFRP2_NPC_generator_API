package com.drzewek.wfrp_npc_generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RaceWriteDto {

    private String name;

    private RaceStatsWriteDto stats;

    private List<String> hairColors;

    private List<String> eyeColors;

    private int minimumAge;

    private int maximumAge;

    private int baseHeight;

    private int minimumWeight;

    private int maximumWeight;
}
