package com.javarush.island.bulimova.entity.organisms.animals;

import com.javarush.island.bulimova.entity.Eater;
import com.javarush.island.bulimova.entity.Move;
import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import com.javarush.island.bulimova.entity.organisms.Organisms;
import com.javarush.island.bulimova.map.Cell;
import com.javarush.island.bulimova.map.Map;
import com.javarush.island.bulimova.util.constants.Constants;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Animals extends Organisms implements Eater, Move {


    public Animals(int COUNT_IN_CELL, String ICON, OrganismsType TYPE, double MAX_WEIGHT_ANIMALS, int SPEED, Gender GENDER) {
        super(COUNT_IN_CELL, ICON, TYPE, MAX_WEIGHT_ANIMALS, SPEED, GENDER);
    }


    @Override
    public void move(Cell currentCell, Map map) {

        this.getLock().lock();
        try {

            int stepLimit = ThreadLocalRandom.current().nextInt(1, this.getCOUNT_IN_CELL() + 1);
            this.setCurrentSpeed(stepLimit);

            double currentWeight = this.getWEIGHT_ANIMALS();
            double penaltyPerMove = this.getWEIGHT_ANIMALS();


            if (currentWeight > penaltyPerMove) {
                boolean tryMove = AnotherCell(map.getMap(), currentCell, stepLimit);

                if (tryMove) {
                    this.setWEIGHT_ANIMALS(currentWeight - penaltyPerMove);
                }


            }

        } finally {
            this.getLock().unlock();


        }

    }

    private boolean AnotherCell(Cell[][] map, Cell currentCell, int stepLimit) {
        int height = map.length;
        int width = map[0].length;

//        if () {
//
//
//
//            currentCell.getOrganism().remove(this);
//            NewCell.getOrganism().add(this);
//            return true;
//        }

        return false;
    }


    @Override
    public void eat(Cell currentCell) {
    }

}

