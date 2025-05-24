package com.javarush.island.lepeshinskij.model.organisms.predators;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.*;

public class Eagle extends Predator {
    private static final double BASE_WEIGHT = 6.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 20;
    private static final int MAX_SPEED = 3;
    private static final double MAX_FOOD = 1.0;

    public Eagle() {
        super(AnimalType.EAGLE, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }

    @Override
    protected Animal createOffspring() {
        return new Eagle();
    }

    @Override
    public double getChanceToEat(Animal prey) {
        if (prey instanceof Fox) return 10;
        if (prey instanceof Rabbit) return 90;
        if (prey instanceof Mouse) return 90;

        return 0;
    }

    @Override
    public String toString() {
        return "Eagle";
    }
}