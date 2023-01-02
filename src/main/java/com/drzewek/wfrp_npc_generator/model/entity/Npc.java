package com.drzewek.wfrp_npc_generator.model.entity;

import com.drzewek.wfrp_npc_generator.model.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Npc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    private int height;

    private int weight;

    private int age;

    private String hairColor;

    private String eyeColor;

//    TODO implement marks generation
//    private String marks;

    private int weaponSkill;

    private int ballisticSkill;

    private int strength;

    private int toughness;

    private int agility;

    private int intelligence;

    private int willPower;

    private int fellowship;

    private int wounds;

    private int strengthBonus;

    private int toughnessBonus;

    private int movement;

    public Npc() {
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getWeaponSkill() {
        return weaponSkill;
    }

    public void setWeaponSkill(int weaponSkill) {
        this.weaponSkill = weaponSkill;
    }

    public int getBallisticSkill() {
        return ballisticSkill;
    }

    public void setBallisticSkill(int ballisticSkill) {
        this.ballisticSkill = ballisticSkill;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWillPower() {
        return willPower;
    }

    public void setWillPower(int willPower) {
        this.willPower = willPower;
    }

    public int getFellowship() {
        return fellowship;
    }

    public void setFellowship(int fellowship) {
        this.fellowship = fellowship;
    }

    public int getWounds() {
        return wounds;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public int getStrengthBonus() {
        return strengthBonus;
    }

    public void setStrengthBonus(int strengthBonus) {
        this.strengthBonus = strengthBonus;
    }

    public int getToughnessBonus() {
        return toughnessBonus;
    }

    public void setToughnessBonus(int toughnessBonus) {
        this.toughnessBonus = toughnessBonus;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }
}
