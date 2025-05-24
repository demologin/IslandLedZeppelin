package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.interfaces.Omnivore;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.utils.RandomGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Boar extends Herbivore implements Omnivore {
    private static final double BASE_WEIGHT = 400.0; // кг
    private static final int MAX_COUNT_PER_LOCATION = 50;
    private static final int MAX_SPEED = 2;
    private static final double MAX_FOOD = 50.0;

    private static final int MOUSE_EATING_PROBABILITY = 50;

    public Boar() {
        super(AnimalType.BOAR, BASE_WEIGHT, MAX_FOOD, MAX_SPEED, MAX_COUNT_PER_LOCATION);
    }

    @Override
    protected Animal createOffspring() {
        return new Boar();
    }

    @Override
    public void eat() {
        if (isHungry()) {
            eatOptimally();
        }
    }

    @Override
    public int getHuntingProbability(Animal prey) {
        if (prey instanceof Mouse) {
            return MOUSE_EATING_PROBABILITY;
        }
        return 0;
    }

    @Override
    public boolean hunt() {
        Location location = getLocation();
        if (location == null) return false;

        List<Mouse> mice = location.getAnimalsByType(Mouse.class);

        List<Animal> aliveMice = mice.stream()
                .filter(mouse -> !mouse.isDead())
                .collect(Collectors.toList());

        boolean huntSuccessful = false;
        for (Animal mouse : aliveMice) {
            if (!isHungry()) break;

            int probability = getHuntingProbability(mouse);
            if (RandomGenerator.getProbability() <= probability) {
                double foodEaten = eatAnimal(mouse);
                huntSuccessful = foodEaten > 0;

                if (!isHungry()) {
                    break;
                }
            }
        }

        return huntSuccessful;
    }

    @Override
    public double eatAnimal(Animal prey) {
        double foodToGain = Math.min(prey.getWeight(), getMaxFood() - getEatenFood());

        increaseHungerLevel(foodToGain);

        prey.die();

        return foodToGain;
    }

    @Override
    public double eatPlants(Location location) {
        if (location == null || location.getPlantsWeight() <= 0) {
            return 0;
        }

        double foodToEat = Math.min(location.getPlantsWeight(), getMaxFood() - getEatenFood());
        increaseHungerLevel(foodToEat);
        location.decreasePlantsWeight(foodToEat);

        return foodToEat;
    }

    @Override
    public void eatOptimally() {
        if (isHungry()) {
            eatPlants(getLocation());
        }
    }

    @Override
    public String toString() {
        return "Boar";
    }
}