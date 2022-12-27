package com.drzewek.wfrp_npc_generator.model.entity;

import com.drzewek.wfrp_npc_generator.model.Gender;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
public class Npc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    private int height;

    private int weight;

    private int age;

    private String hairColor;

    private String eyeColor;

    private String marks;

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

}
