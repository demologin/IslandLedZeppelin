package pyatigin.simulationLife;

import pyatigin.Island.Location;

import java.util.concurrent.locks.Lock;

public class Reproduce {
    public void reproduceOrganism(Location location) {
        Lock lock = location.getLock();

        lock.lock();
        try {
            location.getOrganismsForLocation().forEach(organism -> {
                double countOrganismForLocation = organism.getWightOrganismForLocation() / organism.getWightOrganism();
                if (countOrganismForLocation >= 2) {
                    if (countOrganismForLocation * 2 < organism.getMAX_COUNT_LOCATION()) {
                        organism.grow(organism.getWightOrganismForLocation() * 2);
                    } else {
                        organism.grow((double) organism.getMAX_COUNT_LOCATION() * organism.getWightOrganism());
                    }
                }
            });
        } finally {
            lock.unlock();
        }
    }
}