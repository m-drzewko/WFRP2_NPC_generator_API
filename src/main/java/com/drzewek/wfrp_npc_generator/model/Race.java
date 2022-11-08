package com.drzewek.wfrp_npc_generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Schema(description = "name of the race")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Schema(description = "basic statistics associated with the race")
    private RaceStats stats;

    public Race() {
    }

    public Race(String name, RaceStats stats) {
        this.name = name;
        this.stats = stats;
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
}
