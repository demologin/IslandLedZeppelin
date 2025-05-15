package com.javarush.island.bulimova.entity.organisms.animals;

import com.javarush.island.bulimova.entity.Eater;
import com.javarush.island.bulimova.entity.Reproduce;
import com.javarush.island.bulimova.entity.Run;
import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import com.javarush.island.bulimova.entity.organisms.Organisms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public abstract class Animals extends Organisms implements Eater, Run {


    private final double WEIGHT_ANIMALS;

    private final int SPEED;

    private final double WEIGHT_FOOD;

    private final Gender GENDER;

    public Animals(int COUNT_IN_CELL, String ICON, OrganismsType TYPE, double WEIGHT_ANIMALS, int SPEED, double WEIGHT_FOOD, Gender GENDER) {
        super(COUNT_IN_CELL, ICON, TYPE);
        this.WEIGHT_ANIMALS = WEIGHT_ANIMALS;
        this.SPEED = SPEED;
        this.WEIGHT_FOOD = WEIGHT_FOOD;
        this.GENDER = GENDER;
    }

    @Override
    public void eat() {

    }

    @Override
    public void run() {

    }
}
