package com.javarush.island.lepeshinskij.model.core;

import java.util.ArrayList;
import java.util.List;

public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                locations[y][x] = new Location(x, y);
            }
        }
        SimulationContext.setIsland(this);
    }

    public Location getLocation(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return locations[y][x];
    }

    public List<Location> getAllLocations() {
        List<Location> allLocations = new ArrayList<>(width * height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                allLocations.add(locations[y][x]);
            }
        }
        return allLocations;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}