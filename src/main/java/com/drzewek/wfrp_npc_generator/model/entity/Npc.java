package com.drzewek.wfrp_npc_generator.model.entity;

import com.drzewek.wfrp_npc_generator.model.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Npc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PACKAGE)
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
}
