package com.javarush.island.lepeshinskij.model.interfaces;

import com.javarush.island.lepeshinskij.model.organisms.Animal;

public interface Predation {
    double getChanceToEat(Animal prey);
    
    double eatAnimal(Animal prey);
}