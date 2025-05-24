package com.javarush.island.lepeshinskij.model.organisms.herbivores;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.interfaces.Omnivore;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.utils.RandomGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Duck extends Herbivore implements Omnivore {
    private static final int CATERPILLAR_EATING_PROBABILITY = 90;

    public Duck() {
        super(AnimalType.DUCK);
    }

    @Override
    protected Animal createOffspring() {
        return new Duck();
    }

    @Override
    public void eat() {
        if (isHungry()) {
            eatPlants();
        }
    }

    private boolean huntCaterpillars() {
        Location location = getLocation();
        if (location == null) return false;

        List<Caterpillar> caterpillars = location.getAnimalsByType(Caterpillar.class);

        List<Caterpillar> aliveCaterpillars = caterpillars.stream()
                .filter(caterpillar -> !caterpillar.isDead())
                .collect(Collectors.toList());

        boolean huntSuccessful = false;
        for (Caterpillar caterpillar : aliveCaterpillars) {
            if (!isHungry()) break;

            if (RandomGenerator.getProbability() <= CATERPILLAR_EATING_PROBABILITY) {
                double foodEaten = eatCaterpillar(caterpillar);
                huntSuccessful = foodEaten > 0;

                if (!isHungry()) {
                    break;
                }
            }
        }
        return huntSuccessful;
    }

    private double eatCaterpillar(Animal caterpillar) {
        double foodToGain = Math.min(caterpillar.getWeight(), getMaxFood() - getEatenFood());

        increaseHungerLevel(foodToGain);

        caterpillar.die();

        return foodToGain;
    }

    private double eatPlants() {
        Location location = getLocation();
        if (location == null || location.getPlantsWeight() <= 0) {
            return 0;
        }

        double foodToEat = Math.min(location.getPlantsWeight(), getMaxFood() - getEatenFood());
        increaseHungerLevel(foodToEat);
        location.decreasePlantsWeight(foodToEat);

        return foodToEat;
    }

    @Override
    public String toString() {
        return "Duck";
    }

    @Override
    public int getHuntingProbability(Animal prey) {
        if (prey instanceof Caterpillar) {
            return CATERPILLAR_EATING_PROBABILITY;
        }
        return 0;
    }

    @Override
    public boolean hunt() {
        return huntCaterpillars();
    }

    @Override
    public double eatAnimal(Animal prey) {
        if (prey instanceof Caterpillar) {
            return eatCaterpillar(prey);
        }
        return 0;
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
}