package com.drzewek.wfrp_npc_generator.model.entity;

import com.drzewek.wfrp_npc_generator.model.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Npc npc = (Npc) o;

        if (height != npc.height) return false;
        if (weight != npc.weight) return false;
        if (age != npc.age) return false;
        if (weaponSkill != npc.weaponSkill) return false;
        if (ballisticSkill != npc.ballisticSkill) return false;
        if (strength != npc.strength) return false;
        if (toughness != npc.toughness) return false;
        if (agility != npc.agility) return false;
        if (intelligence != npc.intelligence) return false;
        if (willPower != npc.willPower) return false;
        if (fellowship != npc.fellowship) return false;
        if (wounds != npc.wounds) return false;
        if (strengthBonus != npc.strengthBonus) return false;
        if (toughnessBonus != npc.toughnessBonus) return false;
        if (movement != npc.movement) return false;
        if (!Objects.equals(name, npc.name)) return false;
        if (gender != npc.gender) return false;
        if (!Objects.equals(race, npc.race)) return false;
        if (!Objects.equals(hairColor, npc.hairColor)) return false;
        return Objects.equals(eyeColor, npc.eyeColor);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + height;
        result = 31 * result + weight;
        result = 31 * result + age;
        result = 31 * result + (hairColor != null ? hairColor.hashCode() : 0);
        result = 31 * result + (eyeColor != null ? eyeColor.hashCode() : 0);
        result = 31 * result + weaponSkill;
        result = 31 * result + ballisticSkill;
        result = 31 * result + strength;
        result = 31 * result + toughness;
        result = 31 * result + agility;
        result = 31 * result + intelligence;
        result = 31 * result + willPower;
        result = 31 * result + fellowship;
        result = 31 * result + wounds;
        result = 31 * result + strengthBonus;
        result = 31 * result + toughnessBonus;
        result = 31 * result + movement;
        return result;
    }
}
