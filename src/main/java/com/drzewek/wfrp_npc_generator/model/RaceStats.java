package com.drzewek.wfrp_npc_generator.model;

import javax.persistence.*;

@Entity
public class RaceStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "stats")
    private Race race;

    private int basicWs;
    private int basicBs;
    private int basicStr;
    private int basicTo;
    private int basicAg;
    private int basicInt;
    private int basicWp;
    private int basicFel;

    private int maxWounds;
    private int movement;

    public RaceStats(int basicWs, int basicBs, int basicStr, int basicTo,
                     int basicAg, int basicInt, int basicWp, int basicFel,
                     int maxWounds, int movement) {
        this.basicWs = basicWs;
        this.basicBs = basicBs;
        this.basicStr = basicStr;
        this.basicTo = basicTo;
        this.basicAg = basicAg;
        this.basicInt = basicInt;
        this.basicWp = basicWp;
        this.basicFel = basicFel;
        this.maxWounds = maxWounds;
        this.movement = movement;
    }

    public RaceStats() {
    }
}
