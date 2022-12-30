package com.drzewek.wfrp_npc_generator.utility;

import com.drzewek.wfrp_npc_generator.model.Gender;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Generator {

    public static int generate2d10() {
        Random random = new Random();
        return random.ints(2, 21)
                .findFirst().getAsInt();
    }

    public static int generateD10() {
        Random random = new Random();
        return random.ints(1, 11)
                .findFirst().getAsInt();
    }

    public static Gender generateGender() {
        Random random = new Random();
        boolean isMale = random.nextBoolean();

        if (isMale) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }

    public static int generateFromRange(int min, int max) {
        Random random = new Random();
        return random.ints(min, max + 1).
                findFirst().getAsInt();
    }

    public static int generateWounds(int number) {
        Random random = new Random();
        return number - random.nextInt(4);
    }

    public static HashMap<String, Integer> generateStatistics() {

        HashMap<String, Integer> rolls = new HashMap<>();
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