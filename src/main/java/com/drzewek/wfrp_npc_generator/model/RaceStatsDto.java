package com.drzewek.wfrp_npc_generator.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RaceStatsDto {

    private int basicWeaponSkill;

    private int basicBallisticSkill;

    private int basicStrength;

    private int basicToughness;

    private int basicAgility;

    private int basicIntelligence;

    private int basicWillPower;

    private int basicFellowship;

    private int maxWounds;

    private int movement;
}
