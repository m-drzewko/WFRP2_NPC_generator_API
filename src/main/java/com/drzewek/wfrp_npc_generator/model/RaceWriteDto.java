package com.drzewek.wfrp_npc_generator.model;

public class RaceWriteDto {

    private String name;

    private RaceStatsWriteDto statsDto;

    public RaceWriteDto(String name, RaceStatsWriteDto statsDto) {
        this.name = name;
        this.statsDto = statsDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RaceStatsWriteDto getStatsDto() {
        return statsDto;
    }

    public void setStatsDto(RaceStatsWriteDto statsDto) {
        this.statsDto = statsDto;
    }
}
