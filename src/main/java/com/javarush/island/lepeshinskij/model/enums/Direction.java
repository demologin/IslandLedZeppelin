package com.javarush.island.lepeshinskij.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;
    
    public static List<Direction> getDirections() {
        return Arrays.asList(values());
    }
}