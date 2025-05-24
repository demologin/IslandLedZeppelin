package com.javarush.island.lepeshinskij.model.interfaces;

import com.javarush.island.lepeshinskij.model.core.Location;

import java.util.UUID;

public interface Organism {
    UUID getId();

    double getWeight();

    boolean isDead();

    Location getLocation();
}