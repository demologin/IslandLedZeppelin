package com.javarush.island.bulimova.entity.organisms.plants;

import com.javarush.island.bulimova.entity.organisms.Organism;
import com.javarush.island.bulimova.map.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Grass extends Organism {


    public Grass() {
        super(200, "\uD83C\uDF3F", 1, 0);
    }

    @Override
    public void reproduce(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            long currentCount = currentCell.getOrganism().stream().filter(a -> this.getClass().isInstance(a))
                    .count();

            int maxCount = this.getCOUNT_IN_CELL();

            if (currentCount < this.getCOUNT_IN_CELL()) {
                long missing = maxCount - currentCount;
                long newCurrentCount = ThreadLocalRandom.current().nextLong(1, (missing + 1));

                for (int i = 0; i < newCurrentCount; i++) {
                    Organism clonedOrganism;
                    synchronized (this) {
                        clonedOrganism = this.clone();
                    }
                    currentCell.getOrganism().add(clonedOrganism);
                }
            }


        } finally {
            currentCell.getLock().unlock();


        }
    }
}
