package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

public class Mouse extends Herbivore {
    private static final double BASE_WEIGHT = 0.05; // кг
    private static final int MAX_COUNT_PER_LOCATION = 500;
    private static final int MAX_SPEED = 1;
    private static final double MAX_FOOD = 0.01;
    
    public Mouse() {
        super(AnimalType.MOUSE, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }
    
    @Override
    protected Animal createOffspring() {
        return new Mouse();
    }
    
    @Override
    public String toString() {
        return "Mouse";
    }
}