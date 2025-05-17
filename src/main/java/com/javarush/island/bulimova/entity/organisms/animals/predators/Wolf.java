package com.javarush.island.bulimova.entity.organisms.animals.predators;

import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.enums.OrganismsType;

public class Wolf extends  Predators {

    public Wolf(int COUNT_IN_CELL, String ICON, OrganismsType TYPE, double MAX_WEIGHT_ANIMALS, int SPEED, Gender GENDER) {
        super(COUNT_IN_CELL, ICON, TYPE, MAX_WEIGHT_ANIMALS, SPEED, GENDER);
    }
}
