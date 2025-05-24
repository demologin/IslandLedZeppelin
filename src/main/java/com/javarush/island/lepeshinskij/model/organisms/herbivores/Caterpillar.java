package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.utils.RandomGenerator;

public class Caterpillar extends Herbivore {
    private static final double CATERPILLAR_MAX_FOOD = 0.01;
    
    private static final int REPRODUCTION_PROBABILITY = 30;
    
    public Caterpillar() {
        super(AnimalType.CATERPILLAR);
    }
    
    @Override
    protected Animal createOffspring() {
        return new Caterpillar();
    }
    
    @Override
    public Location chooseDirectionToMove() {
        return getLocation();
    }
    
    @Override
    public void eat() {
        Location location = getLocation();
        if (location != null && location.getPlantsWeight() > 0) {
            double foodToEat = Math.min(location.getPlantsWeight(), CATERPILLAR_MAX_FOOD);
            increaseHungerLevel(foodToEat);
            location.decreasePlantsWeight(foodToEat);
        }
    }
    
    @Override
    public void reproduce() {
        if (isDead()) {
            return;
        }
        
        Location location = getLocation();
        if (location == null) {
            return;
        }
        
        if (location.getAnimalCountByType(getClass()) < getMaxCountPerLocation()) {
            if (RandomGenerator.getProbability() <= REPRODUCTION_PROBABILITY) {
                int offspringCount = RandomGenerator.nextInt(1, 5); // от 1 до 4 потомков
                
                for (int i = 0; i < offspringCount; i++) {
                    if (location.getAnimalCountByType(getClass()) < getMaxCountPerLocation()) {
                        Animal offspring = createOffspring();
                        location.addAnimal(offspring);
                    } else {
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "Caterpillar";
    }
}