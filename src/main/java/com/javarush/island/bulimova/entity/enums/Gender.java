package com.javarush.island.bulimova.entity.enums;

import java.util.concurrent.ThreadLocalRandom;

public enum Gender {

    MALE(1),
    FEMALE(0);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Gender getRandomGender() {
        int randomValue = ThreadLocalRandom.current().nextInt(0, 2); // 0 или 1
        return randomValue == 0 ? FEMALE : MALE;
    }
}
