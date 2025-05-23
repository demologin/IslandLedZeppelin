package com.javarush.island.bulimova.entity.organisms;

import com.javarush.island.bulimova.entity.Reproduce;
import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.map.Cell;
import lombok.Data;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

@Data
public abstract class Organism implements Reproduce, Cloneable {

    private final int COUNT_IN_CELL;

    private final String ICON;

    private final ReentrantLock lock = new ReentrantLock();

    private final double MAX_WEIGHT_ORGANISM;

    private double current_weight;

    private final int SPEED;

    private final Gender GENDER;

    private boolean hungry;

    private final double PENALTY_PER_MOVE;

    private int countOfStep = 0;

    public Organism(int COUNT_IN_CELL, String ICON, double MAX_WEIGHT_ORGANISM, int SPEED) {
        this.COUNT_IN_CELL = COUNT_IN_CELL;
        this.ICON = ICON;
        this.MAX_WEIGHT_ORGANISM = MAX_WEIGHT_ORGANISM;
        this.current_weight = ThreadLocalRandom.current().nextDouble(0, (MAX_WEIGHT_ORGANISM + 1));
        this.SPEED = SPEED;
        this.GENDER = Gender.getRandomGender();
        this.hungry = true;
        this.PENALTY_PER_MOVE = (this.current_weight * 0.05);
    }

    public int getCOUNT_IN_CELL() {
        return COUNT_IN_CELL;
    }

    public String getICON() {
        return ICON;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public double getMAX_WEIGHT_ORGANISM() {
        return MAX_WEIGHT_ORGANISM;
    }

    public double getCurrent_weight() {
        return current_weight;
    }

    public int getSPEED() {
        return SPEED;
    }

    public Gender getGENDER() {
        return GENDER;
    }

    public boolean isHungry() {
        return hungry;
    }

    public double getPENALTY_PER_MOVE() {
        return PENALTY_PER_MOVE;
    }

    public void setCurrent_weight(double current_weight) {
        this.current_weight = current_weight;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    @Override
    public void reproduce(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            long herCount = currentCell.getOrganism().stream().filter(a -> this.getClass().isInstance(a))
                    .count();

            if (herCount < this.getCOUNT_IN_CELL()) {

                Optional<Organism> her = currentCell.getOrganism().stream().filter(a -> this.getClass().isInstance(a))
                        .filter(a -> !a.getGENDER().equals(getGENDER()))
                        .findAny();
                if (!her.isEmpty()) {
                    Organism org = her.get();
                    org.getLock().lock();
                    try {
                        Organism clonedOrganism = (Organism) org.clone();
                        currentCell.getOrganism().add(clonedOrganism);
                        this.setHungry(false);

                    } finally {
                        org.getLock().unlock();
                    }

                }
            }
        } finally {
            this.setHungry(true);
            currentCell.getLock().unlock();


        }
    }

    public Organism clone() {
        try {
            Organism clone = (Organism) super.clone();
            double weight = ThreadLocalRandom.current().nextDouble(current_weight / 2, current_weight);
            clone.current_weight = weight;
            clone.hungry = true;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Ошибка клонирования Organisms", e);
        }
    }

    public void dead(Cell currentCell) {
        this.getLock().lock();
        currentCell.getLock().lock();
        try {
            if (this.countOfStep == 5) {
                currentCell.getOrganism().remove(this);
            }
        } finally {
            this.getLock().unlock();
            currentCell.getLock().unlock();
        }
    }
}
