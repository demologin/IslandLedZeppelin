package com.javarush.island.bulimova.entity.organisms;

import com.javarush.island.bulimova.entity.Reproduce;
import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import com.javarush.island.bulimova.map.Cell;
import lombok.Data;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

@Data
public abstract class Organisms implements Reproduce, Cloneable {

    private final int COUNT_IN_CELL;

    private final String ICON;

    private final OrganismsType TYPE;

    private final ReentrantLock lock = new ReentrantLock();

    private double WEIGHT_ANIMALS;

    private final double MAX_WEIGHT_ANIMALS;

    private final int SPEED;

    private final Gender GENDER;

    private boolean hungry = true;

    private final double PENALTY_PER_MOVE = 0.2;

    @Override
    public void reproduce(Cell currentCell) {

        try {
            currentCell.getLock().lock();
            long herCount = currentCell.getOrganism().stream().filter(a -> this.getClass().isInstance(a))
                    .count();

            if (herCount < this.getCOUNT_IN_CELL()) {

                Optional<Organisms> her = currentCell.getOrganism().stream().filter(a -> this.getClass().isInstance(a))
                        .filter(a -> !a.getGENDER().equals(getGENDER()))
                        .findAny();
                if (!her.isEmpty()) {
                    Organisms org = her.get();
                    try {
                        org.getLock().lock();
                        Organisms clonedOrganism = (Organisms) org.clone();
                        currentCell.getOrganism().add(clonedOrganism);

                    } finally {
                        org.getLock().unlock();
                    }

                }
            }
        } finally {
            currentCell.getLock().unlock();

        }
    }

    public Organisms clone() {
        try {
            Organisms clone = (Organisms) super.clone();
            double weight = ThreadLocalRandom.current().nextDouble(WEIGHT_ANIMALS / 2, WEIGHT_ANIMALS);
            clone.WEIGHT_ANIMALS = weight;
            clone.hungry = true;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Ошибка клонирования Organisms", e);
        }
    }

}
