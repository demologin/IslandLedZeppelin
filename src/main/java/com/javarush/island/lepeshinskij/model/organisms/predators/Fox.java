package com.javarush.island.lepeshinskij.model.organisms.predators;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.Mouse;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.Rabbit;

public class Fox extends Predator {
    private static final double BASE_WEIGHT = 8.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 30;
    private static final int MAX_SPEED = 2;
    private static final double MAX_FOOD = 2.0;

    public Fox() {
        super(AnimalType.FOX, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }

    @Override
    protected Animal createOffspring() {
        return new Fox();
    }

    @Override
    public double getChanceToEat(Animal prey) {
        if (prey instanceof Rabbit) return 70;
        if (prey instanceof Mouse) return 90;

        return 0;
    }

    @Override
    public String toString() {
        return "Fox";
    }
}