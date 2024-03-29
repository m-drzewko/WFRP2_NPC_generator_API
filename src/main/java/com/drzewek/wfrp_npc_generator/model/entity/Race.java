package com.drzewek.wfrp_npc_generator.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "name of the race")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Schema(description = "basic statistics associated with the race")
    private RaceStats stats;

    @ElementCollection
    @Schema(description = "list of possible hair colors for each race, in English")
    private List<String> hairColors = new ArrayList<>();

    @ElementCollection
    @Schema(description = "list of possible hair colors for each race, in Polish")
    private List<String> hairColorsPl = new ArrayList<>();

    @ElementCollection
    @Schema(description = "list of possible eye colors for each race, in English")
    private List<String> eyeColors = new ArrayList<>();

    @ElementCollection
    @Schema(description = "list of possible eye colors for each race, in Polish")
    private List<String> eyeColorsPl = new ArrayList<>();

    @Schema(description = "minimum age a generated NPC of a race can be")
    private int minimumAge;

    @Schema(description = "maximum age a generated NPC of a race can be")
    private int maximumAge;

    @Schema(description = "base height for a generated male NPC of a race")
    private int baseHeight;

    @Schema(description = "minimum weight a generated NPC of a race can be")
    private int minimumWeight;

    @Schema(description = "maximum weight a generated NPC of a race can be")
    private int maximumWeight;

}
