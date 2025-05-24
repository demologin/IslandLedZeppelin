package com.javarush.island.lepeshinskij.model.interfaces;

import com.javarush.island.lepeshinskij.model.core.Location;

public interface Movable {
    void move(Location newLocation);
    
    Location chooseDirectionToMove();
    
    int getMaxSpeed();
}