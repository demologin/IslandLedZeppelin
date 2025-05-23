package pyatigin.simulationLife;

import pyatigin.Island.Location;
import pyatigin.abstractClass.Animal;
import pyatigin.abstractClass.Organism;
import pyatigin.util.RandomGenerator;

import java.util.List;

public class Movement {
    public void moveOrganism(Location location) {
        RandomGenerator randomGenerator = new RandomGenerator();
        List<Organism> organisms = location.getOrganismsForLocation();

        for (Organism organism : organisms) {
            if (organism instanceof Animal) {
                Animal animal = (Animal) organism;
                if (animal.getCountAnimals() > 0) {
                    var randomLocation = location.getNeighboursLocations().getRandomLocation(randomGenerator);
                    int count = randomGenerator.nextInt(animal.getCountAnimals() + 1);

                    location.getLock().lock();
                    try {
                        if (animal.move(randomLocation, count)) {
                            animal.updateWightOrganismForLocation(count);
                        }
                    } finally {
                        location.getLock().unlock();
                    }
                }
            }
        }
    }
}
