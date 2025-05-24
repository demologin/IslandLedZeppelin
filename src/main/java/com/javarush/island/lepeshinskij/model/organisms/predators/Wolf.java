package com.javarush.island.lepeshinskij.model.organisms.predators;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.Deer;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.Goat;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.Horse;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.Mouse;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.Rabbit;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.Sheep;

public class Wolf extends Predator {
    private static final double BASE_WEIGHT = 50.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 30;
    private static final int MAX_SPEED = 3;
    private static final double MAX_FOOD = 5.0;

    public Wolf() {
        super(AnimalType.WOLF, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }

    @Override
    protected Animal createOffspring() {
        return new Wolf();
    }

    @Override
    public double getChanceToEat(Animal prey) {
        if (prey instanceof Horse) return 10;
        if (prey instanceof Deer) return 15;
        if (prey instanceof Rabbit) return 60;
        if (prey instanceof Mouse) return 80;
        if (prey instanceof Goat) return 60;
        if (prey instanceof Sheep) return 70;

        return 0;
    }

    @Override
    public String toString() {
        return "Wolf";
    }
}