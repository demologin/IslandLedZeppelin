package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

public class Sheep extends Herbivore {
    private static final double BASE_WEIGHT = 70.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 140;
    private static final int MAX_SPEED = 3;
    private static final double MAX_FOOD = 15.0;
    
    public Sheep() {
        super(AnimalType.SHEEP, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }
    
    @Override
    protected Animal createOffspring() {
        return new Sheep();
    }
    
    @Override
    public String toString() {
        return "Sheep";
    }
}