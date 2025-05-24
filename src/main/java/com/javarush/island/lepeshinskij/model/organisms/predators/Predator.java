package com.javarush.island.lepeshinskij.model.organisms.predators;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.enums.Direction;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.interfaces.Predation;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.utils.RandomGenerator;

import java.util.List;

public abstract class Predator extends Animal implements Predation {

    protected Predator(AnimalType animalType, double weight, double maxFood, int maxSpeed, int maxCountPerLocation) {
        super(animalType, weight, maxFood, maxSpeed, maxCountPerLocation);
    }

    @Override
    public void eat() {
        Location location = getLocation();
        if (location == null) return;

        List<Animal> preyList = location.getAnimals().stream()
                .filter(animal -> !animal.isDead() && animal != this && getChanceToEat(animal) > 0)
                .sorted((a, b) -> {
                    double chanceA = getChanceToEat(a);
                    double chanceB = getChanceToEat(b);
                    return Double.compare(chanceB, chanceA);
                })
                .toList();

        if (preyList.isEmpty()) {
            for (Direction direction : Direction.getDirections()) {
                Location neighbor = location.getNeighbor(direction, 1);
                if (neighbor != null) {
                    List<Animal> neighborPrey = neighbor.getAnimals().stream()
                            .filter(animal -> !animal.isDead() && getChanceToEat(animal) > 0)
                            .toList();
                    if (!neighborPrey.isEmpty()) {
                        move(neighbor);
                        location = neighbor;
                        preyList = neighborPrey.stream()
                                .sorted((a, b) -> {
                                    double chanceA = getChanceToEat(a);
                                    double chanceB = getChanceToEat(b);
                                    return Double.compare(chanceB, chanceA);
                                })
                                .toList();
                        break;
                    }
                }
            }
        }

        double huntingBoost = 1.2;

        for (Animal prey : preyList) {
            if (getEatenFood() >= getMaxFood()) {
                break;
            }

            double eatProbability = Math.min(100, getChanceToEat(prey) * huntingBoost);
            if (eatProbability > 0 && RandomGenerator.getProbability() <= eatProbability) {
                eatAnimal(prey);
            }
        }
    }

    @Override
    public abstract double getChanceToEat(Animal prey);

    @Override
    public double eatAnimal(Animal prey) {
        double foodGained = Math.min(prey.getWeight(), getMaxFood() - getEatenFood());

        increaseHungerLevel(foodGained);

        prey.die();

        return foodGained;
    }
}