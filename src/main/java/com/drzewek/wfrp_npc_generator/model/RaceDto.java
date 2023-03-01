package com.drzewek.wfrp_npc_generator.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RaceDto {

    private String name;

    private RaceStatsDto stats;

    private List<String> hairColors;

    private List<String> eyeColors;

    private int minimumAge;

    private int maximumAge;

    private int baseHeight;

    private int minimumWeight;

    private int maximumWeight;
}
