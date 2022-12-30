package com.drzewek.wfrp_npc_generator.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    public Race() {
    }

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RaceStats getStats() {
        return stats;
    }

    public void setStats(RaceStats stats) {
        this.stats = stats;
    }

    public List<String> getHairColors() {
        return hairColors;
    }

    public void setHairColors(List<String> hairColors) {
        this.hairColors = hairColors;
    }

    public List<String> getEyeColors() {
        return eyeColors;
    }

    public void setEyeColors(List<String> eyeColors) {
        this.eyeColors = eyeColors;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public int getBaseHeight() {
        return baseHeight;
    }

    public void setBaseHeight(int baseHeight) {
        this.baseHeight = baseHeight;
    }

    public int getMinimumWeight() {
        return minimumWeight;
    }

    public void setMinimumWeight(int minimumWeight) {
        this.minimumWeight = minimumWeight;
    }

    public int getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(int maximumWeight) {
        this.maximumWeight = maximumWeight;
    }
}
