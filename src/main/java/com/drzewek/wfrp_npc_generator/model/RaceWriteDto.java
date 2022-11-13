package com.drzewek.wfrp_npc_generator.model;

public class RaceWriteDto {

    private String name;

    private RaceStatsWriteDto stats;

    public RaceWriteDto(String name, RaceStatsWriteDto stats) {
        this.name = name;
        this.stats = stats;
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
}
