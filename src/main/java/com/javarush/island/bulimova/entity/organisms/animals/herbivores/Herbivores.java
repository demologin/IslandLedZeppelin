package com.javarush.island.bulimova.entity.organisms.animals.herbivores;

import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import com.javarush.island.bulimova.entity.organisms.animals.Animals;

public class Herbivores extends Animals {

    public Herbivores(int COUNT_IN_CELL, String ICON, OrganismsType TYPE, double WEIGHT_ANIMALS, int SPEED, double WEIGHT_FOOD, Gender GENDER) {
        super(COUNT_IN_CELL, ICON, TYPE, WEIGHT_ANIMALS, SPEED, WEIGHT_FOOD, GENDER);
    }
}
