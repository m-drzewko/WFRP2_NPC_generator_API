package com.drzewek.wfrp_npc_generator.utility;

import com.drzewek.wfrp_npc_generator.model.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Generator {

    private static final Random random = new Random();

    public static int generateD4() {
        return random.ints(1, 5)
                .findFirst().getAsInt();
    }

    public static int generate2d10() {
        return random.ints(2, 21)
                .findFirst().getAsInt();
    }

    public static int generateD10() {
        return random.ints(1, 11)
                .findFirst().getAsInt();
    }

    public static Gender generateGender() {
        boolean isMale = random.nextBoolean();

        if (isMale) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }

    public static int generateFromRange(int min, int max) {
        return random.ints(min, max + 1).
                findFirst().getAsInt();
    }

    public static int generateWounds(int number) {
        return number - random.nextInt(4);
    }

    public static Map<String, Integer> generateStatistics() {

        LinkedHashMap<String, Integer> rolls = new LinkedHashMap<>();
        rolls.put("weaponSkill", generate2d10());
        rolls.put("ballisticSkill", generate2d10());
        rolls.put("strength", generate2d10());
        rolls.put("toughness", generate2d10());
        rolls.put("agility", generate2d10());
        rolls.put("intelligence", generate2d10());
        rolls.put("willPower", generate2d10());
        rolls.put("fellowship", generate2d10());

//        apply Shalya's Mercy
        AtomicReference<String> lowestStat = new AtomicReference<>("");
        AtomicReference<Integer> lowestRoll = new AtomicReference<>(100);

        rolls.entrySet().stream().forEach(entry -> {
            if (entry.getValue() < lowestRoll.get()) {
                lowestRoll.set(entry.getValue());
                lowestStat.set(entry.getKey());
            }
        });
        rolls.replace(String.valueOf(lowestStat), 11);

        return rolls;
    }
}