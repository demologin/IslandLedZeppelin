package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

public class Horse extends Herbivore {
    private static final double BASE_WEIGHT = 400.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 20;
    private static final int MAX_SPEED = 4;
    private static final double MAX_FOOD = 60.0;

    public Horse() {
        super(AnimalType.HORSE, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }

    @Override
    protected Animal createOffspring() {
        return new Horse();
    }

    @Override
    public String toString() {
        return "Horse";
    }
}