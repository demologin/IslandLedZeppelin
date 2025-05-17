package com.javarush.island.bulimova.entity.organisms.plants;

import com.javarush.island.bulimova.entity.Reproduce;
import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import com.javarush.island.bulimova.entity.organisms.Organisms;
import com.javarush.island.bulimova.entity.organisms.animals.herbivores.Herbivores;
import com.javarush.island.bulimova.map.Cell;

import java.util.Optional;

public class Grass extends Organisms {


    public Grass(int COUNT_IN_CELL, String ICON, OrganismsType TYPE, double MAX_WEIGHT_ANIMALS, int SPEED, Gender GENDER) {
        super(COUNT_IN_CELL, ICON, TYPE, MAX_WEIGHT_ANIMALS, SPEED, GENDER);
    }
}
