package com.javarush.island.bulimova.entity.organisms.animals;

import com.javarush.island.bulimova.entity.Eater;
import com.javarush.island.bulimova.entity.Move;
import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import com.javarush.island.bulimova.entity.organisms.Organisms;
import com.javarush.island.bulimova.map.Cell;
import com.javarush.island.bulimova.map.Map;
import com.javarush.island.bulimova.util.constants.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

            if (this.getWEIGHT_ANIMALS() > this.getPENALTY_PER_MOVE() * stepLimit) {
                Cell targetCell = anotherCell(map.getMap(), currentCell, stepLimit);


                if (targetCell != currentCell) {

                    currentCell.getOrganism().remove(this);

                    targetCell.getOrganism().add(this);


                    this.setWEIGHT_ANIMALS(getWEIGHT_ANIMALS() - this.getPENALTY_PER_MOVE() * stepLimit);
                }
            }

        } finally {
            this.getLock().unlock();


        }

    }

    private Cell anotherCell(Cell[][] map, Cell currentCell, int stepLimit) {

        int widthX = map.length;
        int heightY = map[0].length;

        int x = currentCell.getX();
        int y = currentCell.getY();


        List<Cell> coordinate = new ArrayList<>();

        if (x + stepLimit < widthX) {

            coordinate.add(map[x + stepLimit][y]);
        }
        if (x - stepLimit >= 0) {
            coordinate.add(map[x - stepLimit][y]);
        }
        if (y + stepLimit < heightY) {
            coordinate.add(map[x][y + stepLimit]);
        }
        if (y - stepLimit >= 0) {
            coordinate.add(map[x][y - stepLimit]);
        }
        coordinate.add(currentCell);

        Collections.shuffle(coordinate);

        for (Cell targetCell : coordinate) {


            if (!targetCell.getLock().tryLock()) continue;

            try {
                long sameTypeCount = targetCell.getOrganism().stream()
                        .filter(o -> this.getClass().isInstance(o))
                        .count();

                if (sameTypeCount < this.getCOUNT_IN_CELL()) {
                    return targetCell;
                }


            } finally {
                targetCell.getLock().unlock();
            }
        }
        return currentCell;
    }

    @Override
    public void eat(Cell currentCell) {
    }

}

