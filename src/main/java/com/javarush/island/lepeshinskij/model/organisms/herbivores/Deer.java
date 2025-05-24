package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

public class Deer extends Herbivore {
    private static final double BASE_WEIGHT = 300.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 20;
    private static final int MAX_SPEED = 4;
    private static final double MAX_FOOD = 50.0;

    public Deer() {
        super(AnimalType.DEER, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }

    @Override
    protected Animal createOffspring() {
        return new Deer();
    }

    @Override
    public String toString() {
        return "Deer";
    }
}