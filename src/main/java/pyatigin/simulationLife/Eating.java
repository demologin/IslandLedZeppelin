package pyatigin.simulationLife;

import pyatigin.Island.FoodMap;
import pyatigin.Island.FoodMapOrganism;
import pyatigin.Island.Location;

import pyatigin.abstractClass.Animal;
import pyatigin.abstractClass.Organism;
import pyatigin.util.RandomGenerator;

import java.util.concurrent.locks.Lock;

public class Eating {
    public void eatingOrganism(Location location, FoodMap foodMap) {
        RandomGenerator rand = new RandomGenerator();
        Lock lock = location.getLock();
        lock.lock();
        try {
            for (int i = 0; i < location.getOrganismsForLocation().size(); i++) {
                if (location.getOrganismsForLocation().get(i) instanceof Animal) {
                    Animal predator = (Animal) location.getOrganismsForLocation().get(i);
                    FoodMapOrganism foodMapOrganism = foodMap.getFoodMap(predator.getName());
                    if (foodMapOrganism != null) {
                        double remainingFood = 0.0;
                        for (int j = 0; j < location.getOrganismsForLocation().size(); j++) {
                            if (i != j) {
                                Organism food = location.getOrganismsForLocation().get(j);
                                if (food.getWightOrganism() == 0.0) {
                                    continue;
                                }
                                int probabilityToEat = foodMapOrganism.getFoodItem(food.getName());
                                int random = rand.nextInt(100);
                                if (random < probabilityToEat) {
                                    double foodResult = predator.eat(food, predator.getNeedFoodKg() * predator.getWightOrganismForLocation());

                                    if (foodResult == 0.0) {
                                        break;
                                    } else {
                                        remainingFood += foodResult;
                                    }
                                }
                            }
                        }
                        if (remainingFood > 0) {
                           int result =(int) Math.ceil(remainingFood / predator.getNeedFoodKg());
                            predator.die(result*predator.getWightOrganism()*1.0);
                        }
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }
}

