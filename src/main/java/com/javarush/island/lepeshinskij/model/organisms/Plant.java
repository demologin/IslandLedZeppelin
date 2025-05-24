package com.javarush.island.lepeshinskij.model.organisms;

import com.javarush.island.lepeshinskij.config.SimulationConfig;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.interfaces.Eatable;
import com.javarush.island.lepeshinskij.model.interfaces.Organism;

import java.util.UUID;

public class Plant implements Organism, Eatable {
    private static final double WEIGHT = SimulationConfig.PLANT_WEIGHT;
    private final UUID id;
    private Location location;
    private boolean dead;

    public Plant(Location location) {
        this.id = UUID.randomUUID();
        this.location = location;
        this.dead = false;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public double getWeight() {
        return WEIGHT;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void beEaten() {
        this.dead = true;
    }
}