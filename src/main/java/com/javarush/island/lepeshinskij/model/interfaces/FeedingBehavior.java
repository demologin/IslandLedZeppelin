package com.javarush.island.lepeshinskij.model.interfaces;

public interface FeedingBehavior {
    void eat();
    
    boolean isHungry();
    
    void increaseHungerLevel(double amount);
    
    double getMaxFood();
    
    double getEatenFood();
}