package com.javarush.island.lepeshinskij.model.organisms.predators;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.*;

public class Bear extends Predator {
    private static final double BASE_WEIGHT = 500.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 5;
    private static final int MAX_SPEED = 2;
    private static final double MAX_FOOD = 80.0;

    public Bear() {
        super(AnimalType.BEAR, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }

    @Override
    protected Animal createOffspring() {
        return new Bear();
    }

    @Override
    public double getChanceToEat(Animal prey) {
        if (prey instanceof Snake) return 80;
        if (prey instanceof Horse) return 40;
        if (prey instanceof Deer) return 80;
        if (prey instanceof Rabbit) return 80;
        if (prey instanceof Mouse) return 90;
        if (prey instanceof Goat) return 70;
        if (prey instanceof Sheep) return 70;

        return 0;
    }

    @Override
    public String toString() {
        return "Bear";
    }
}