package pyatigin.Island;

import lombok.Getter;
import pyatigin.abstractClass.Organism;
import pyatigin.factory.OrganismFactory;
import pyatigin.util.RandomGenerator;

import java.util.*;


public class Island {
    @Getter
    private final int sizeHeight = 100;
    @Getter
    private final int sizeWidth = 20;
    @Getter
    private List<Organism> organismsIsland;//TODO вынести в отдельный класс
    private final Location[][] locations = new Location[sizeHeight][sizeWidth];
    @Getter
    private FoodMap foodMap;

    public void IslandCreate(OrganismFactory organismFactory) {
        organismsIsland = organismFactory.createRandomLifeFromAllAnnotatedClasses();
        RandomGenerator randomGenerator = new RandomGenerator();
        for (int i = 0; i < sizeHeight; i++) {
            for (int j = 0; j < sizeWidth; j++) {
                Location location = new Location(i, j);
                location.locationLifeInit(this, randomGenerator, organismFactory);
                locations[i][j] = location;
            }
        }

        FoodMap foodMap = new FoodMap(organismsIsland);
        this.foodMap = foodMap;

        for (int i = 0; i < sizeHeight; i++) {
            for (int j = 0; j < sizeWidth; j++) {
                locations[i][j].locationNeighbours(this);
            }
        }
    }
    public Location getLocationIsland(int x, int y) {
        return locations[x][y];
    }
    public Location[][] getLocationsIsland() {
        return locations;
    }
}

