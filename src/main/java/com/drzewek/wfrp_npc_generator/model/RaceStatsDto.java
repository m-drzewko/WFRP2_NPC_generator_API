package com.drzewek.wfrp_npc_generator.model;

public class RaceStatsDto {

    private String name;

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

    public RaceStatsDto(String name, int basicWs, int basicBs, int basicStr,
                        int basicTo, int basicAg, int basicInt, int basicWp,
                        int basicFel, int maxWounds, int movement) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBasicWs() {
        return basicWs;
    }

    public void setBasicWs(int basicWs) {
        this.basicWs = basicWs;
    }

    public int getBasicBs() {
        return basicBs;
    }

    public void setBasicBs(int basicBs) {
        this.basicBs = basicBs;
    }

    public int getBasicStr() {
        return basicStr;
    }

    public void setBasicStr(int basicStr) {
        this.basicStr = basicStr;
    }

    public int getBasicTo() {
        return basicTo;
    }

    public void setBasicTo(int basicTo) {
        this.basicTo = basicTo;
    }

    public int getBasicAg() {
        return basicAg;
    }

    public void setBasicAg(int basicAg) {
        this.basicAg = basicAg;
    }

    public int getBasicInt() {
        return basicInt;
    }

    public void setBasicInt(int basicInt) {
        this.basicInt = basicInt;
    }

    public int getBasicWp() {
        return basicWp;
    }

    public void setBasicWp(int basicWp) {
        this.basicWp = basicWp;
    }

    public int getBasicFel() {
        return basicFel;
    }

    public void setBasicFel(int basicFel) {
        this.basicFel = basicFel;
    }

    public int getMaxWounds() {
        return maxWounds;
    }

    public void setMaxWounds(int maxWounds) {
        this.maxWounds = maxWounds;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }
}
