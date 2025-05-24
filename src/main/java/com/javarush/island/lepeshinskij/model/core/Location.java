package com.javarush.island.lepeshinskij.model.core;

import com.javarush.island.lepeshinskij.config.SimulationConfig;
import com.javarush.island.lepeshinskij.model.enums.Direction;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Location implements Comparable<Location> {
    private final int x;
    private final int y;
    private final List<Animal> animals;
    private final double maxPlantsWeight;
    private final ReentrantLock lock = new ReentrantLock();
    private double plantsWeight;
    private int plantsCount;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = Collections.synchronizedList(new ArrayList<>());
        this.plantsWeight = 0;
        this.plantsCount = 0;
        this.maxPlantsWeight = SimulationConfig.MAX_PLANTS_PER_LOCATION * SimulationConfig.PLANT_WEIGHT;
    }

    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public synchronized List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public synchronized <T extends Animal> List<T> getAnimalsByType(Class<T> animalClass) {
        return animals.stream()
                .filter(animal -> animalClass.isInstance(animal))
                .map(animal -> (T) animal)
                .collect(Collectors.toList());
    }

    public synchronized int getAnimalCountByType(Class<? extends Animal> animalClass) {
        return (int) animals.stream()
                .filter(animal -> animalClass.isInstance(animal))
                .count();
    }

    public Location getNeighbor(Direction direction, int distance) {
        Island island = SimulationContext.getIsland();
        if (island == null) {
            return null;
        }

        int newX = x;
        int newY = y;

        switch (direction) {
            case NORTH -> newY -= distance;
            case EAST -> newX += distance;
            case SOUTH -> newY += distance;
            case WEST -> newX -= distance;
        }

        return island.getLocation(newX, newY);
    }

    public synchronized double getPlantsWeight() {
        return plantsWeight;
    }

    public synchronized void setPlantsWeight(double weight) {
        this.plantsWeight = Math.min(weight, maxPlantsWeight);
        this.plantsCount = (int) (this.plantsWeight / SimulationConfig.PLANT_WEIGHT);
    }

    public synchronized int getPlantsCount() {
        return plantsCount;
    }

    public synchronized double decreasePlantsWeight(double amount) {
        double actualAmount = Math.min(amount, plantsWeight);
        plantsWeight -= actualAmount;
        int plantsEaten = (int)(actualAmount / SimulationConfig.PLANT_WEIGHT);
        plantsCount = Math.max(0, plantsCount - plantsEaten);
        return actualAmount;
    }

    public double getMaxPlantsWeight() {
        return maxPlantsWeight;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    @Override
    public int compareTo(Location other) {
        if (this.y != other.y) {
            return Integer.compare(this.y, other.y);
        }
        return Integer.compare(this.x, other.x);
    }

    @Override
    public String toString() {
        return "Location(" + x + "," + y + ")";
    }
}