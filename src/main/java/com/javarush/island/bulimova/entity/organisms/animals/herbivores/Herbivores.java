package com.javarush.island.bulimova.entity.organisms.animals.herbivores;

import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import com.javarush.island.bulimova.entity.organisms.Organisms;
import com.javarush.island.bulimova.entity.organisms.animals.Animals;
import com.javarush.island.bulimova.entity.organisms.plants.Grass;
import com.javarush.island.bulimova.map.Cell;

import java.util.Optional;

public class Herbivores extends Animals {

    public Herbivores(int COUNT_IN_CELL, String ICON, OrganismsType TYPE, double MAX_WEIGHT_ANIMALS, int SPEED, Gender GENDER) {
        super(COUNT_IN_CELL, ICON, TYPE, MAX_WEIGHT_ANIMALS, SPEED, GENDER);
    }

    public void eat(Cell currentCell) {

        Optional<Organisms> her = currentCell.getOrganism().stream().filter(a -> a instanceof Grass)
                .filter(a -> a.getWEIGHT_ANIMALS() > 0.0)
                .findAny();

        if (!her.isEmpty()) {
            Organisms org = her.get();
            try {
                org.getLock().lock();
                double currentWeight = this.getWEIGHT_ANIMALS();
                double maxWeight = this.getMAX_WEIGHT_ANIMALS();
                double otherWeight = org.getWEIGHT_ANIMALS();

                double availableWeight = maxWeight - currentWeight;
                double weightToAdd = Math.min(availableWeight, otherWeight);
                this.setWEIGHT_ANIMALS(currentWeight + weightToAdd);
                org.setWEIGHT_ANIMALS(0.0);
                currentCell.getOrganism().remove(org);

            } finally {
                org.getLock().unlock();
            }

            this.setHungry(false);
        }

    }
}
