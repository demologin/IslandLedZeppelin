package com.javarush.island.bulimova.entity.organisms.animals.predators;

import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.organisms.Organisms;
import com.javarush.island.bulimova.entity.organisms.animals.herbivores.Herbivores;
import com.javarush.island.bulimova.map.Cell;

import java.util.Optional;

public class Predators extends Organisms {

    public Predators(int COUNT_IN_CELL, String ICON, double MAX_WEIGHT_ORGANISM, int SPEED) {
        super(COUNT_IN_CELL, ICON, MAX_WEIGHT_ORGANISM, SPEED);
    }

    public void eat(Cell currentCell) {

        Optional<Organisms> her = currentCell.getOrganism().stream().filter(a -> a instanceof Herbivores)
                .filter(a -> a.getCurrent_weight() > 0.0)
                .findAny();

        if (!her.isEmpty()) {
            Organisms org = her.get();
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
