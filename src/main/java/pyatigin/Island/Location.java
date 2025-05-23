package pyatigin.Island;

import lombok.Getter;
import pyatigin.abstractClass.Organism;
import pyatigin.factory.OrganismFactory;
import pyatigin.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Location {
    private final int x;
    private final int y;
    private List<Organism> organismsForLocation; //TODO Сделать объектом
    private NeighboursLocation neighboursLocations;
    private final ReentrantLock lock = new ReentrantLock();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.organismsForLocation = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}' + this.organismsForLocation.stream().toString();
    }

    public void addOrganism(Organism organism) {
        organismsForLocation.add(organism);
    }


    public Organism getOrganism(String name) {
        lock.lock();
        try {
            return organismsForLocation.stream().filter(organism -> organism.getName().equals(name)).findFirst().orElse(null);
        } finally {
            lock.unlock();
        }
    }

    public boolean searchOrganism(String organismName) {
        return organismsForLocation.stream().anyMatch(organism -> organism.getName().equals(organismName));
    }

    public void locationLifeInit(Island island, RandomGenerator random, OrganismFactory organismFactory) {
        island.getOrganismsIsland().forEach(organism -> {
            //TODO Вернуть заселение рандомное
            if (random.nextBoolean()) {
            int wight = organism.getWightOrganism();
            int generatedCount = random.nextInt(organism.getMAX_COUNT_LOCATION() + 1);
            Organism organismForLocation = organismFactory.createLife(organism.getClass());
            organismForLocation.setWightOrganismForLocation(generatedCount * wight);
            organismsForLocation.add(organismForLocation);
            }
        });
    }

    public void locationNeighbours(Island island) {
        this.neighboursLocations = new NeighboursLocation(this, island);
    }
}
