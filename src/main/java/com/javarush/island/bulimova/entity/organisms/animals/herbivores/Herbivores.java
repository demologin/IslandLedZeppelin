package com.javarush.island.bulimova.entity.organisms.animals.herbivores;

import com.javarush.island.bulimova.entity.organisms.Organism;
import com.javarush.island.bulimova.entity.organisms.animals.Animals;
import com.javarush.island.bulimova.entity.organisms.plants.Grass;
import com.javarush.island.bulimova.map.Cell;

import java.util.Optional;

public class Herbivores extends Animals {

    public Herbivores(int COUNT_IN_CELL, String ICON, double MAX_WEIGHT_ORGANISM, int SPEED) {
        super(COUNT_IN_CELL, ICON, MAX_WEIGHT_ORGANISM, SPEED);
    }

    public void eat(Cell currentCell) {

        Optional<Organism> her = currentCell.getOrganism().stream().filter(a -> a instanceof Grass)
                .filter(a -> a.getCurrent_weight() > 0.0)
                .findAny();

        if (!her.isEmpty()) {
            Organism org = her.get();
            try {
                org.getLock().lock();
                double currentWeight = this.getCurrent_weight();
                double maxWeight = this.getMAX_WEIGHT_ORGANISM();
                double otherWeight = org.getCurrent_weight();

                double availableWeight = maxWeight - currentWeight;
                double weightToAdd = Math.min(availableWeight, otherWeight);
                this.setCurrent_weight(currentWeight + weightToAdd);
                org.setCurrent_weight(0.0);
                currentCell.getOrganism().remove(org);

            } finally {
                org.getLock().unlock();
            }

            this.setHungry(false);
        }

    }
}
