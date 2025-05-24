package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

public class Goat extends Herbivore {
    private static final double BASE_WEIGHT = 60.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 140;
    private static final int MAX_SPEED = 3;
    private static final double MAX_FOOD = 10.0;
    
    public Goat() {
        super(AnimalType.GOAT, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }
    
    @Override
    protected Animal createOffspring() {
        return new Goat();
    }
    
    @Override
    public String toString() {
        return "Goat";
    }
}