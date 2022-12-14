package com.drzewek.wfrp_npc_generator.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Schema(description = "basic weapon skill of a generated character")
    private int basicWeaponSkill;

    @Schema(description = "basic ballistic skill of a generated character")
    private int basicBallisticSkill;

    @Schema(description = "basic strength of a generated character")
    private int basicStrength;

    @Schema(description = "basic thoughness of a generated character")
    private int basicToughness;

    @Schema(description = "basic agility of a generated character")
    private int basicAgility;

    @Schema(description = "basic intelligence of a generated character")
    private int basicIntelligence;

    @Schema(description = "basic will power of a generated character")
    private int basicWillPower;

    @Schema(description = "basic fellowship of a generated character")
    private int basicFellowship;

    @Schema(description = "maximum wounds the character can have after generation")
    private int maxWounds;

    @Schema(description = "character's movement speed")
    private int movement;

    public RaceStats(int basicWeaponSkill, int basicBallisticSkill, int basicStrength, int basicToughness,
                     int basicAgility, int basicIntelligence, int basicWillPower, int basicFellowship,
                     int maxWounds, int movement) {
        this.basicWeaponSkill = basicWeaponSkill;
        this.basicBallisticSkill = basicBallisticSkill;
        this.basicStrength = basicStrength;
        this.basicToughness = basicToughness;
        this.basicAgility = basicAgility;
        this.basicIntelligence = basicIntelligence;
        this.basicWillPower = basicWillPower;
        this.basicFellowship = basicFellowship;
        this.maxWounds = maxWounds;
        this.movement = movement;
    }
}
