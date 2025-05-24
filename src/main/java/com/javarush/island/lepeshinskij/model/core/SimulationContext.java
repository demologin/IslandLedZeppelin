package com.javarush.island.lepeshinskij.model.core;

public class SimulationContext {
    private static Island island;

    public static void setIsland(Island island) {
        SimulationContext.island = island;
    }

    public static Island getIsland() {
        return island;
    }
}