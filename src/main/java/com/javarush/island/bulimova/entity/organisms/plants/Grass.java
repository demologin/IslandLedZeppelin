package com.javarush.island.bulimova.entity.organisms.plants;

import com.javarush.island.bulimova.entity.Reproduce;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import com.javarush.island.bulimova.entity.organisms.Organisms;

public class Grass extends Organisms {

    public Grass(int COUNT_IN_CELL, String ICON, OrganismsType TYPE) {
        super(COUNT_IN_CELL, ICON, TYPE);
    }
}
