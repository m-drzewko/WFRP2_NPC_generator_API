package com.drzewek.wfrp_npc_generator.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Schema(description = "list of possible hair colors for each race")
    private List<String> hairColors = new ArrayList<>();

    @ElementCollection
    @Schema(description = "list of possible eye colors for each race")
    private List<String> eyeColors = new ArrayList<>();

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

    public Race(String name, RaceStats stats, List<String> hairColors,
                List<String> eyeColors, int minimumAge, int maximumAge,
                int baseHeight, int minimumWeight, int maximumWeight) {
        this.name = name;
        this.stats = stats;
        this.hairColors = hairColors;
        this.eyeColors = eyeColors;
        this.minimumAge = minimumAge;
        this.maximumAge = maximumAge;
        this.baseHeight = baseHeight;
        this.minimumWeight = minimumWeight;
        this.maximumWeight = maximumWeight;
    }

    public Race(String name) {
        this.name = name;
    }
}
