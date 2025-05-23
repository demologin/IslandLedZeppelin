package pyatigin.simulationLife;

import pyatigin.Island.FoodMap;

import pyatigin.Island.Island;
import pyatigin.Island.Location;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lifecycle {
    private final Island island;
    private final FoodMap foodMap;
    private final ExecutorService threadPool;

    public Lifecycle(Island island) {
        this.island = island;
        this.foodMap = island.getFoodMap();
        this.threadPool = Executors.newCachedThreadPool();
    }

    public void start() {
        Location[][] locationsIsland = island.getLocationsIsland();
        for (Location[] locations : locationsIsland) {
            for (final Location location : locations) {
                threadPool.submit(() -> processLocation(location));
            }
        }
        threadPool.shutdown();
    }

    private void processLocation(Location location) {
        Eating eating = new Eating();
        Reproduce reproduce = new Reproduce();
        Movement movement = new Movement();

        eating.eatingOrganism(location, foodMap);
        reproduce.reproduceOrganism(location);
        movement.moveOrganism(location);
    }
}


