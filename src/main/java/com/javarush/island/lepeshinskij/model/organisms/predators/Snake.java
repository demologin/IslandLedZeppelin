package com.javarush.island.lepeshinskij.model.organisms.predators;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.*;

public class Snake extends Predator {
    private static final double BASE_WEIGHT = 15.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 30;
    private static final int MAX_SPEED = 1;
    private static final double MAX_FOOD = 3.0;
    
    public Snake() {
        super(AnimalType.SNAKE, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }
    
    @Override
    protected Animal createOffspring() {
        return new Snake();
    }
    
    @Override
    public double getChanceToEat(Animal prey) {
        if (prey instanceof Fox) return 15;
        if (prey instanceof Rabbit) return 20;
        if (prey instanceof Mouse) return 40;
        
        return 0;
    }
    
    @Override
    public String toString() {
        return "Snake";
    }
}