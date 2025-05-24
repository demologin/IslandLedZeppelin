package com.javarush.island.lepeshinskij.simulation.stats;

import com.javarush.island.lepeshinskij.model.core.Island;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.*;
import com.javarush.island.lepeshinskij.model.organisms.predators.*;
import com.javarush.island.lepeshinskij.service.OutputFactory;
import com.javarush.island.lepeshinskij.service.OutputService;

import java.util.HashMap;
import java.util.Map;

public class SimulationStatistics implements Runnable {
    private final OutputService output = OutputFactory.getOutputService();
    private final Island island;
    private final int day;

    private static final int MAX_BAR_LENGTH = 50;
    private static final Map<Class<? extends Animal>, String> ANIMAL_SYMBOLS = new HashMap<>();

    static {
        ANIMAL_SYMBOLS.put(Wolf.class, "🐺");
        ANIMAL_SYMBOLS.put(Snake.class, "🐍");
        ANIMAL_SYMBOLS.put(Fox.class, "🦊");
        ANIMAL_SYMBOLS.put(Bear.class, "🐻");
        ANIMAL_SYMBOLS.put(Eagle.class, "🦅");
        ANIMAL_SYMBOLS.put(Horse.class, "🐎");
        ANIMAL_SYMBOLS.put(Deer.class, "🦌");
        ANIMAL_SYMBOLS.put(Rabbit.class, "🐇");
        ANIMAL_SYMBOLS.put(Mouse.class, "🐁");
        ANIMAL_SYMBOLS.put(Goat.class, "🐐");
        ANIMAL_SYMBOLS.put(Sheep.class, "🐑");
        ANIMAL_SYMBOLS.put(Boar.class, "🐗");
        ANIMAL_SYMBOLS.put(Buffalo.class, "🐃");
        ANIMAL_SYMBOLS.put(Duck.class, "🦆");
        ANIMAL_SYMBOLS.put(Caterpillar.class, "🐛");
    }

    public SimulationStatistics(Island island, int day) {
        this.island = island;
        this.day = day;
    }

    @Override
    public void run() {
        Map<Class<? extends Animal>, Integer> animalCounts = countAnimals();
        double totalPlants = countPlants();

        displayStatistics(animalCounts, totalPlants);
    }

    private Map<Class<? extends Animal>, Integer> countAnimals() {
        Map<Class<? extends Animal>, Integer> counts = new HashMap<>();

        counts.put(Wolf.class, 0);
        counts.put(Snake.class, 0);
        counts.put(Fox.class, 0);
        counts.put(Bear.class, 0);
        counts.put(Eagle.class, 0);
        counts.put(Horse.class, 0);
        counts.put(Deer.class, 0);
        counts.put(Rabbit.class, 0);
        counts.put(Mouse.class, 0);
        counts.put(Goat.class, 0);
        counts.put(Sheep.class, 0);
        counts.put(Boar.class, 0);
        counts.put(Buffalo.class, 0);
        counts.put(Duck.class, 0);
        counts.put(Caterpillar.class, 0);

        for (Location location : island.getAllLocations()) {
            for (Class<? extends Animal> animalClass : counts.keySet()) {
                int count = location.getAnimalCountByType(animalClass);
                counts.put(animalClass, counts.get(animalClass) + count);
            }
        }

        return counts;
    }

    private double countPlants() {
        double totalPlants = 0;

        for (Location location : island.getAllLocations()) {
            totalPlants += location.getPlantsWeight();
        }

        return totalPlants;
    }

    private String createGraphBar(int count, int maxCount, String symbol) {
        if (maxCount <= 0) return "";

        int barLength = (int) Math.ceil(((double) count / maxCount) * MAX_BAR_LENGTH);
        barLength = Math.min(barLength, MAX_BAR_LENGTH);

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            bar.append(symbol);
        }

        return bar.toString();
    }

    private void displayStatistics(Map<Class<? extends Animal>, Integer> animalCounts, double totalPlants) {
        output.println("\n============= ДЕНЬ " + day + " =============");
        output.println("СТАТИСТИКА ПОПУЛЯЦИИ:");

        int maxCount = animalCounts.values().stream().mapToInt(Integer::intValue).max().orElse(1);

        output.println("\nХИЩНИКИ:");
        displayAnimalWithGraph(animalCounts, Wolf.class, "Волки", maxCount);
        displayAnimalWithGraph(animalCounts, Snake.class, "Удавы", maxCount);
        displayAnimalWithGraph(animalCounts, Fox.class, "Лисы", maxCount);
        displayAnimalWithGraph(animalCounts, Bear.class, "Медведи", maxCount);
        displayAnimalWithGraph(animalCounts, Eagle.class, "Орлы", maxCount);

        output.println("\nТРАВОЯДНЫЕ:");
        displayAnimalWithGraph(animalCounts, Horse.class, "Лошади", maxCount);
        displayAnimalWithGraph(animalCounts, Deer.class, "Олени", maxCount);
        displayAnimalWithGraph(animalCounts, Rabbit.class, "Кролики", maxCount);
        displayAnimalWithGraph(animalCounts, Mouse.class, "Мыши", maxCount);
        displayAnimalWithGraph(animalCounts, Goat.class, "Козы", maxCount);
        displayAnimalWithGraph(animalCounts, Sheep.class, "Овцы", maxCount);
        displayAnimalWithGraph(animalCounts, Boar.class, "Кабаны", maxCount);
        displayAnimalWithGraph(animalCounts, Buffalo.class, "Буйволы", maxCount);

        output.println("\nВСЕЯДНЫЕ:");
        displayAnimalWithGraph(animalCounts, Duck.class, "Утки", maxCount);

        output.println("\nОСОБЫЕ:");
        displayAnimalWithGraph(animalCounts, Caterpillar.class, "Гусеницы", maxCount);

        output.println("\nРАСТЕНИЯ:");
        output.printf("Общий вес растений: %.2f кг%n", totalPlants);

        int plantsScaled = (int) (totalPlants / 10);
        String plantGraph = createGraphBar(plantsScaled, plantsScaled, "🌿");
        output.println(plantGraph);

        int totalAnimals = animalCounts.values().stream().mapToInt(Integer::intValue).sum();
        output.println("\nОБЩЕЕ КОЛИЧЕСТВО ЖИВОТНЫХ: " + totalAnimals);

        output.println("=====================================\n");
    }

    private void displayAnimalWithGraph(Map<Class<? extends Animal>, Integer> animalCounts,
                                        Class<? extends Animal> animalClass,
                                        String animalName,
                                        int maxCount) {
        int count = animalCounts.get(animalClass);
        String symbol = ANIMAL_SYMBOLS.getOrDefault(animalClass, "●");
        String graph = createGraphBar(count, maxCount, symbol);
        output.printf("%-10s: %-5d %s%n", animalName, count, graph);
    }
}