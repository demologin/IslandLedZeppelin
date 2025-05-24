package com.javarush.island.lepeshinskij.model.interfaces;

import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.organisms.Animal;

public interface Omnivore {
    int getHuntingProbability(Animal prey);
    
    boolean hunt();
    
    double eatAnimal(Animal prey);
    
    double eatPlants(Location location);
    
    void eatOptimally();
}