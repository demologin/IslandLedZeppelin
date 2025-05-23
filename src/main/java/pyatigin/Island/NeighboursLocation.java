package pyatigin.Island;

import pyatigin.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;


public class NeighboursLocation {
    private final List<Location> neighbours;
    private final Location location;
    private final Island island;
    public NeighboursLocation(Location location,Island island) {
        this.location = location;
        this.neighbours = new ArrayList<>();
        this.island = island;
        createNeighbours();
    }
    public Location getRandomLocation(RandomGenerator random) {
        return neighbours.get(random.nextInt(neighbours.size()));
    }
    private void createNeighbours() {
        int x = location.getX();
        int y = location.getY();
        int maxX = island.getSizeHeight();
        int maxY = island.getSizeWidth();
        if (x > 0) {
            neighbours.add(island.getLocationIsland(x - 1, y)); // Влево
        }
        if (x < maxX - 1) {
            neighbours.add(island.getLocationIsland(x + 1, y)); // Вправо
        }
        if (y > 0) {
            neighbours.add(island.getLocationIsland(x, y - 1)); // Вверх
        }
        if (y < maxY - 1) {
            neighbours.add(island.getLocationIsland(x, y + 1)); // Вниз
        }
    }
}
