package com.javarush.island.lepeshinskij.model.organisms.herbivores;
import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.config.SimulationConfig;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

public abstract class Herbivore extends Animal {

    protected Herbivore(AnimalType animalType, double weight, double maxFood, int maxSpeed, int maxCountPerLocation) {
        super(animalType, weight, maxFood, maxSpeed, maxCountPerLocation);
    }

    protected Herbivore(AnimalType animalType) {
        super(
            animalType,
            SimulationConfig.getCharacteristics(animalType).getWeight(),
            SimulationConfig.getCharacteristics(animalType).getMaxFood(),
            SimulationConfig.getCharacteristics(animalType).getMaxSpeed(),
            SimulationConfig.getCharacteristics(animalType).getMaxCountPerLocation()
        );
    }

    @Override
    public void eat() {
        Location location = getLocation();
        if (location != null && location.getPlantsWeight() > 0) {
            double foodToEat = Math.min(location.getPlantsWeight(), getMaxFood() - getEatenFood());
            increaseHungerLevel(foodToEat);
            location.decreasePlantsWeight(foodToEat);
        }
    }

    @Override
    public void reproduce() {
        super.reproduce();
    }

    @Override
    protected abstract Animal createOffspring();
}