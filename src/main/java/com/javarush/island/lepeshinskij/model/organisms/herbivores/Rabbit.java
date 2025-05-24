package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

public class Rabbit extends Herbivore {
    private static final double BASE_WEIGHT = 2.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 150;
    private static final int MAX_SPEED = 2;
    private static final double MAX_FOOD = 0.45;
    
    public Rabbit() {
        super(AnimalType.RABBIT, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }
    
    @Override
    protected Animal createOffspring() {
        return new Rabbit();
    }
    
    @Override
    public String toString() {
        return "Rabbit";
    }
}