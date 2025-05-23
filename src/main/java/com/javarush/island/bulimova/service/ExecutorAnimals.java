package com.javarush.island.bulimova.service;


import com.javarush.island.bulimova.entity.organisms.Organism;
import com.javarush.island.bulimova.entity.organisms.animals.Animals;
import com.javarush.island.bulimova.map.Cell;
import com.javarush.island.bulimova.map.Map;
import lombok.Data;

import java.util.concurrent.Callable;


@Data
public class ExecutorAnimals implements Callable<Void> {

    private volatile Cell cell;

    private volatile Map map;


    public ExecutorAnimals(Cell cell, Map map) {
        this.cell = cell;
        this.map = map;
    }

    @Override
    public Void call() {

        cell.getLock().lock();
        try {
            for (Organism org : cell.getOrganism()) {
                org.getLock().lock();
                try {
                    if (org instanceof Animals) {
                        ((Animals) org).move(cell, map);
                        ((Animals) org).eat(cell);
                        org.reproduce(cell);
                        org.dead(cell);
                    } else {
                        org.reproduce(cell);
                    }
                } finally {
                    org.getLock().unlock();
                }
            }
        } finally {
            cell.getLock().unlock();
        }

        return null;


    }

}


