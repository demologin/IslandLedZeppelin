package com.javarush.island.lepeshinskij.config;

import com.javarush.island.lepeshinskij.model.enums.AnimalType;

import java.util.EnumMap;
import java.util.Map;

public class SimulationConfig {
    public static final int ISLAND_WIDTH = 100;
    public static final int ISLAND_HEIGHT = 20;

    public static final int SIMULATION_DAYS = 30;
    public static final long TICK_DURATION_MS = 1000;

    public static final double PLANT_WEIGHT = 1.0;
    public static final int MAX_PLANTS_PER_LOCATION = 200;
    public static final double PLANT_GROWTH_RATE = 0.1;

    public static final int INITIAL_WOLF_COUNT = 30;
    public static final int INITIAL_SNAKE_COUNT = 30;
    public static final int INITIAL_FOX_COUNT = 30;
    public static final int INITIAL_BEAR_COUNT = 5;
    public static final int INITIAL_EAGLE_COUNT = 20;
    public static final int INITIAL_HORSE_COUNT = 20;
    public static final int INITIAL_DEER_COUNT = 20;
    public static final int INITIAL_RABBIT_COUNT = 150;
    public static final int INITIAL_MOUSE_COUNT = 500;
    public static final int INITIAL_GOAT_COUNT = 140;
    public static final int INITIAL_SHEEP_COUNT = 140;
    public static final int INITIAL_BOAR_COUNT = 50;
    public static final int INITIAL_BUFFALO_COUNT = 10;
    public static final int INITIAL_DUCK_COUNT = 200;
    public static final int INITIAL_CATERPILLAR_COUNT = 1000;

    public static final int MIN_ANIMALS_TO_CONTINUE = 10;
    public static final int PREDATOR_REPRODUCTION_PROBABILITY = 30;
    public static final int HERBIVORE_REPRODUCTION_PROBABILITY = 60;

    private static final Map<AnimalType, AnimalCharacteristics> ANIMAL_CHARACTERISTICS = new EnumMap<>(AnimalType.class);

    private static final Map<AnimalType, Integer> INITIAL_COUNTS = new EnumMap<>(AnimalType.class);

    static {
        ANIMAL_CHARACTERISTICS.put(AnimalType.WOLF, new AnimalCharacteristics(50, 8, 3, 30));
        ANIMAL_CHARACTERISTICS.put(AnimalType.SNAKE, new AnimalCharacteristics(15, 3, 1, 30));
        ANIMAL_CHARACTERISTICS.put(AnimalType.FOX, new AnimalCharacteristics(8, 2, 2, 30));
        ANIMAL_CHARACTERISTICS.put(AnimalType.BEAR, new AnimalCharacteristics(500, 80, 2, 5));
        ANIMAL_CHARACTERISTICS.put(AnimalType.EAGLE, new AnimalCharacteristics(6, 1, 3, 20));

        ANIMAL_CHARACTERISTICS.put(AnimalType.HORSE, new AnimalCharacteristics(400, 60, 4, 20));
        ANIMAL_CHARACTERISTICS.put(AnimalType.DEER, new AnimalCharacteristics(300, 50, 4, 20));
        ANIMAL_CHARACTERISTICS.put(AnimalType.RABBIT, new AnimalCharacteristics(2, 0.45, 2, 150));
        ANIMAL_CHARACTERISTICS.put(AnimalType.MOUSE, new AnimalCharacteristics(0.05, 0.01, 1, 500));
        ANIMAL_CHARACTERISTICS.put(AnimalType.GOAT, new AnimalCharacteristics(60, 10, 3, 140));
        ANIMAL_CHARACTERISTICS.put(AnimalType.SHEEP, new AnimalCharacteristics(70, 15, 3, 140));
        ANIMAL_CHARACTERISTICS.put(AnimalType.BOAR, new AnimalCharacteristics(400, 50, 2, 50));
        ANIMAL_CHARACTERISTICS.put(AnimalType.BUFFALO, new AnimalCharacteristics(700, 100, 3, 10));
        ANIMAL_CHARACTERISTICS.put(AnimalType.DUCK, new AnimalCharacteristics(1, 0.15, 4, 200));
        ANIMAL_CHARACTERISTICS.put(AnimalType.CATERPILLAR, new AnimalCharacteristics(0.01, 0, 0, 1000));

        INITIAL_COUNTS.put(AnimalType.WOLF, INITIAL_WOLF_COUNT);
        INITIAL_COUNTS.put(AnimalType.SNAKE, INITIAL_SNAKE_COUNT);
        INITIAL_COUNTS.put(AnimalType.FOX, INITIAL_FOX_COUNT);
        INITIAL_COUNTS.put(AnimalType.BEAR, INITIAL_BEAR_COUNT);
        INITIAL_COUNTS.put(AnimalType.EAGLE, INITIAL_EAGLE_COUNT);
        INITIAL_COUNTS.put(AnimalType.HORSE, INITIAL_HORSE_COUNT);
        INITIAL_COUNTS.put(AnimalType.DEER, INITIAL_DEER_COUNT);
        INITIAL_COUNTS.put(AnimalType.RABBIT, INITIAL_RABBIT_COUNT);
        INITIAL_COUNTS.put(AnimalType.MOUSE, INITIAL_MOUSE_COUNT);
        INITIAL_COUNTS.put(AnimalType.GOAT, INITIAL_GOAT_COUNT);
        INITIAL_COUNTS.put(AnimalType.SHEEP, INITIAL_SHEEP_COUNT);
        INITIAL_COUNTS.put(AnimalType.BOAR, INITIAL_BOAR_COUNT);
        INITIAL_COUNTS.put(AnimalType.BUFFALO, INITIAL_BUFFALO_COUNT);
        INITIAL_COUNTS.put(AnimalType.DUCK, INITIAL_DUCK_COUNT);
        INITIAL_COUNTS.put(AnimalType.CATERPILLAR, INITIAL_CATERPILLAR_COUNT);
    }

    public static AnimalCharacteristics getCharacteristics(AnimalType animalType) {
        return ANIMAL_CHARACTERISTICS.get(animalType);
    }

    public static int getInitialCount(AnimalType animalType) {
        Integer count = INITIAL_COUNTS.get(animalType);
        return count != null ? count : 0;
    }

    public static class AnimalCharacteristics {
        private final double weight;
        private final double maxFood;
        private final int maxSpeed;
        private final int maxCountPerLocation;

        public AnimalCharacteristics(double weight, double maxFood, int maxSpeed, int maxCountPerLocation) {
            this.weight = weight;
            this.maxFood = maxFood;
            this.maxSpeed = maxSpeed;
            this.maxCountPerLocation = maxCountPerLocation;
        }

        public double getWeight() {
            return weight;
        }

        public double getMaxFood() {
            return maxFood;
        }

        public int getMaxSpeed() {
            return maxSpeed;
        }

        public int getMaxCountPerLocation() {
            return maxCountPerLocation;
        }
    }
}