package com.drzewek.wfrp_npc_generator.model;

public class RaceStatsWriteDto {

    private int basicWeaponSkill;

    private int basicBallisticSkill;

    private int basicStrength;

    private int basicToughness;

    private int basicAgility;

    private int basicIntelligence;

    private int basicWillPower;

    private int basicFellowship;

    private int maxWounds;

    private int movement;

    public RaceStatsWriteDto(int basicWeaponSkill, int basicBallisticSkill,
                             int basicStrength, int basicToughness, int basicAgility,
                             int basicIntelligence, int basicWillPower, int basicFellowship,
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

    public RaceStatsWriteDto() {
    }

    public int getBasicWeaponSkill() {
        return basicWeaponSkill;
    }

    public void setBasicWeaponSkill(int basicWeaponSkill) {
        this.basicWeaponSkill = basicWeaponSkill;
    }

    public int getBasicBallisticSkill() {
        return basicBallisticSkill;
    }

    public void setBasicBallisticSkill(int basicBallisticSkill) {
        this.basicBallisticSkill = basicBallisticSkill;
    }

    public int getBasicStrength() {
        return basicStrength;
    }

    public void setBasicStrength(int basicStrength) {
        this.basicStrength = basicStrength;
    }

    public int getBasicToughness() {
        return basicToughness;
    }

    public void setBasicToughness(int basicToughness) {
        this.basicToughness = basicToughness;
    }

    public int getBasicAgility() {
        return basicAgility;
    }

    public void setBasicAgility(int basicAgility) {
        this.basicAgility = basicAgility;
    }

    public int getBasicIntelligence() {
        return basicIntelligence;
    }

    public void setBasicIntelligence(int basicIntelligence) {
        this.basicIntelligence = basicIntelligence;
    }

    public int getBasicWillPower() {
        return basicWillPower;
    }

    public void setBasicWillPower(int basicWillPower) {
        this.basicWillPower = basicWillPower;
    }

    public int getBasicFellowship() {
        return basicFellowship;
    }

    public void setBasicFellowship(int basicFellowship) {
        this.basicFellowship = basicFellowship;
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
