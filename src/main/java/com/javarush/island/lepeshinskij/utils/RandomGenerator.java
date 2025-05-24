package com.javarush.island.lepeshinskij.utils;

import java.util.Random;

public class RandomGenerator {
    private static final Random random = new Random();
    
    public static int getProbability() {
        return random.nextInt(101);
    }

    public static int nextInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    
    public static double nextDouble() {
        return random.nextDouble();
    }
}
