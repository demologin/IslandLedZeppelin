package com.javarush.island.lepeshinskij.model.organisms;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.enums.Direction;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.interfaces.Organism;
import com.javarush.island.lepeshinskij.model.interfaces.Movable;
import com.javarush.island.lepeshinskij.model.interfaces.Lifecycle;
import com.javarush.island.lepeshinskij.model.interfaces.FeedingBehavior;
import com.javarush.island.lepeshinskij.model.interfaces.Eatable;
import com.javarush.island.lepeshinskij.model.organisms.predators.Predator;
import com.javarush.island.lepeshinskij.utils.RandomGenerator;

import java.util.List;
import java.util.UUID;

public abstract class Animal implements Organism, Movable, Lifecycle, FeedingBehavior, Eatable {
    private final UUID id;
    private final double weight;
    private final double maxFood;
    private final int maxSpeed;
    private final int maxCountPerLocation;
    private final AnimalType animalType;
    
    private Location location;
    private double eatenFood;
    private boolean dead;

    protected Animal(AnimalType animalType, double weight, double maxFood, int maxSpeed, int maxCountPerLocation) {
        this.id = UUID.randomUUID();
        this.animalType = animalType;
        this.weight = weight;
        this.maxFood = maxFood;
        this.maxSpeed = maxSpeed;
        this.maxCountPerLocation = maxCountPerLocation;
        this.eatenFood = 0;
        this.dead = false;
    }
    
    @Override
    public void move(Location newLocation) {
        if (location != null) {
            location.removeAnimal(this);
    }
        this.location = newLocation;
        if (newLocation != null) {
            newLocation.addAnimal(this);
}
    }

    @Override
    public Location chooseDirectionToMove() {
        if (maxSpeed == 0 || dead || location == null) {
            return location;
        }

        List<Direction> directions = Direction.getDirections();
        Direction randomDirection = directions.get(RandomGenerator.nextInt(0, directions.size() - 1));

        int distance = 1 + RandomGenerator.nextInt(0, maxSpeed - 1);

        Location target = location.getNeighbor(randomDirection, distance);

        if (target == null) {
            return location;
        }

        int currentCount = target.getAnimalCountByType(getClass());
        if (currentCount >= maxCountPerLocation) {
            return location;
        }

        return target;
    }

    @Override
    public void reproduce() {
        if (dead || location == null) {
            return;
        }

        List<Animal> sameSpecies = location.getAnimalsByType((Class<Animal>) getClass());

        sameSpecies.remove(this);

        if (!sameSpecies.isEmpty()) {
            if (location.getAnimalCountByType(getClass()) < maxCountPerLocation) {
                int offspringCount = RandomGenerator.nextInt(1, 3);
                for (int i = 0; i < offspringCount; i++) {
                    if (location.getAnimalCountByType(getClass()) < maxCountPerLocation) {
                        Animal offspring = createOffspring();
                        location.addAnimal(offspring);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    protected abstract Animal createOffspring();

    @Override
    public abstract void eat();

    @Override
    public void increaseHungerLevel(double amount) {
        this.eatenFood += amount;
        if (this.eatenFood > maxFood) {
            this.eatenFood = maxFood;
        }
    }

    @Override
    public boolean isHungry() {
        if (this instanceof Predator) {
            return eatenFood < maxFood * 0.3;
        }
        return eatenFood < maxFood * 0.5;
    }

    @Override
    public boolean decreaseHungerLevel() {
        eatenFood -= maxFood * 0.10;

        if (eatenFood <= 0) {
            eatenFood = 0;
            if (RandomGenerator.getProbability() > 85) {
                die();
                return true;
            }
        }
        return false;
    }

    @Override
    public void beEaten() {
        die();
    }

    @Override
    public void die() {
        this.dead = true;
        if (location != null) {
            location.removeAnimal(this);
            this.location = null;
        }
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public double getMaxFood() {
        return maxFood;
    }

    @Override
    public double getEatenFood() {
        return eatenFood;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxCountPerLocation() {
        return maxCountPerLocation;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    @Override
    public String toString() {
        return animalType != null ? animalType.name() : getClass().getSimpleName();
    }
}