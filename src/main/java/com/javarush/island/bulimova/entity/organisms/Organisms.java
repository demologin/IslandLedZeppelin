package com.javarush.island.bulimova.entity.organisms;

import com.javarush.island.bulimova.entity.Reproduce;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

@Data
public abstract class Organisms implements Reproduce {

    private final int COUNT_IN_CELL;

    private final String ICON;

    private final OrganismsType TYPE;

    private final ReentrantLock lock = new ReentrantLock();

    public Organisms(int COUNT_IN_CELL, String ICON, OrganismsType TYPE) {
        this.COUNT_IN_CELL = COUNT_IN_CELL;
        this.ICON = ICON;
        this.TYPE = TYPE;
    }

    @Override
    public void reproduce() {

    }
}
