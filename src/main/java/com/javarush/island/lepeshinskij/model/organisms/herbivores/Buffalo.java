package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

public class Buffalo extends Herbivore {
    private static final double BASE_WEIGHT = 700.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 10;
    private static final int MAX_SPEED = 3;
    private static final double MAX_FOOD = 100.0;

    public Buffalo() {
        super(AnimalType.BUFFALO, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }

    @Override
    protected Animal createOffspring() {
        return new Buffalo();
    }

    @Override
    public String toString() {
        return "Buffalo";
    }
}