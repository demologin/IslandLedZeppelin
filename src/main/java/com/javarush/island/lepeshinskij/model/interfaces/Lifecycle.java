package com.javarush.island.lepeshinskij.model.interfaces;

public interface Lifecycle {
    void eat();
    
    void reproduce();
    
    boolean decreaseHungerLevel();
    
    void die();
}