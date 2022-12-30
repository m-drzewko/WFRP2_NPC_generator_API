package com.drzewek.wfrp_npc_generator.model;

import java.util.List;

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

    public RaceWriteDto(String name, RaceStatsWriteDto stats, List<String> hairColors,
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

    public RaceWriteDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RaceStatsWriteDto getStats() {
        return stats;
    }

    public void setStats(RaceStatsWriteDto stats) {
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
