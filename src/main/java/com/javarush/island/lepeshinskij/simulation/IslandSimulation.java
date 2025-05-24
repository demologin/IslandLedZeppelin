package com.javarush.island.lepeshinskij.simulation;

import com.javarush.island.lepeshinskij.config.SimulationConfig;
import com.javarush.island.lepeshinskij.model.core.Island;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.*;
import com.javarush.island.lepeshinskij.model.organisms.predators.*;
import com.javarush.island.lepeshinskij.service.OutputFactory;
import com.javarush.island.lepeshinskij.service.OutputService;
import com.javarush.island.lepeshinskij.simulation.lifecycle.AnimalLifecycle;
import com.javarush.island.lepeshinskij.simulation.lifecycle.PlantLifecycle;
import com.javarush.island.lepeshinskij.simulation.stats.SimulationStatistics;
import com.javarush.island.lepeshinskij.visualization.IslandMapRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class IslandSimulation {
    private final OutputService output = OutputFactory.getOutputService();
    private static final double INITIAL_PLANTS_PERCENTAGE = 0.5;
    private static final int BARRIER_PARTIES_COUNT = 2;
    private static final int THREAD_POOL_SIZE = 3;
    private static final int SHUTDOWN_TIMEOUT_SECONDS = 5;
    private static final int DISPLAY_MODE_WITH_MAP = 2;

    private static final Map<AnimalType, Class<? extends Animal>> ANIMAL_CLASSES = new HashMap<>();

    static {
        ANIMAL_CLASSES.put(AnimalType.WOLF, Wolf.class);
        ANIMAL_CLASSES.put(AnimalType.SNAKE, Snake.class);
        ANIMAL_CLASSES.put(AnimalType.FOX, Fox.class);
        ANIMAL_CLASSES.put(AnimalType.BEAR, Bear.class);
        ANIMAL_CLASSES.put(AnimalType.EAGLE, Eagle.class);

        ANIMAL_CLASSES.put(AnimalType.HORSE, Horse.class);
        ANIMAL_CLASSES.put(AnimalType.DEER, Deer.class);
        ANIMAL_CLASSES.put(AnimalType.RABBIT, Rabbit.class);
        ANIMAL_CLASSES.put(AnimalType.MOUSE, Mouse.class);
        ANIMAL_CLASSES.put(AnimalType.GOAT, Goat.class);
        ANIMAL_CLASSES.put(AnimalType.SHEEP, Sheep.class);
        ANIMAL_CLASSES.put(AnimalType.BOAR, Boar.class);
        ANIMAL_CLASSES.put(AnimalType.BUFFALO, Buffalo.class);
        ANIMAL_CLASSES.put(AnimalType.DUCK, Duck.class);
        ANIMAL_CLASSES.put(AnimalType.CATERPILLAR, Caterpillar.class);
    }

    private final Island island;
    private final AtomicBoolean running;
    private final CyclicBarrier tickBarrier;
    private final ScheduledExecutorService scheduler;
    private final AnimalLifecycle animalLifecycle;
    private final PlantLifecycle plantLifecycle;

    private int currentDay;
    private int displayMode = DISPLAY_MODE_WITH_MAP;

    public IslandSimulation() {
        this.island = new Island(SimulationConfig.ISLAND_WIDTH, SimulationConfig.ISLAND_HEIGHT);
        this.running = new AtomicBoolean(true);
        this.tickBarrier = new CyclicBarrier(BARRIER_PARTIES_COUNT);
        this.scheduler = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
        this.animalLifecycle = new AnimalLifecycle(island, running, tickBarrier);
        this.plantLifecycle = new PlantLifecycle(island, running);
        this.currentDay = 0;

        initializeIsland();
    }

    public void setDisplayMode(int mode) {
        this.displayMode = mode;
    }

    private void initializeIsland() {
        for (Location location : island.getAllLocations()) {
            double initialPlants = location.getMaxPlantsWeight() * INITIAL_PLANTS_PERCENTAGE;
            location.setPlantsWeight(initialPlants);
        }

        for (AnimalType animalType : AnimalType.values()) {
            if (animalType == AnimalType.PLANT) {
                continue;
            }

            Class<? extends Animal> animalClass = ANIMAL_CLASSES.get(animalType);
            int initialCount = SimulationConfig.getInitialCount(animalType);

            if (animalClass != null && initialCount > 0) {
                placeAnimalsRandomly(animalClass, initialCount);
            }
        }
    }

    private <T extends Animal> void placeAnimalsRandomly(Class<T> animalClass, int count) {
        List<Location> locations = island.getAllLocations();

        for (int i = 0; i < count; i++) {
            try {
                T animal = animalClass.getDeclaredConstructor().newInstance();

                Location randomLocation;
                do {
                    randomLocation = locations.get((int) (Math.random() * locations.size()));
                } while (randomLocation.getAnimalCountByType(animalClass) >= animal.getMaxCountPerLocation());

                animal.move(randomLocation);
            } catch (Exception e) {
                output.println("Ошибка при создании животного " + animalClass.getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    public void start() {
        if (running.get()) {
            output.println("Запуск симуляции острова");
            output.println("Размер острова: " + SimulationConfig.ISLAND_WIDTH + "x" + SimulationConfig.ISLAND_HEIGHT);
            output.println("Максимальное количество дней: " + SimulationConfig.SIMULATION_DAYS);
            output.println("Длительность такта: " + SimulationConfig.TICK_DURATION_MS + " мс");

            scheduler.scheduleAtFixedRate(animalLifecycle, 0,
                    SimulationConfig.TICK_DURATION_MS, TimeUnit.MILLISECONDS);
            scheduler.scheduleAtFixedRate(plantLifecycle, 0,
                    SimulationConfig.TICK_DURATION_MS, TimeUnit.MILLISECONDS);

            while (running.get() && currentDay < SimulationConfig.SIMULATION_DAYS) {
                try {
                    output.println("===== День " + currentDay + " =====");

                    SimulationStatistics statistics = new SimulationStatistics(island, currentDay);
                    statistics.run();

                    if (displayMode == DISPLAY_MODE_WITH_MAP) {
                        IslandMapRenderer.renderMap(island);
                    }

                    int totalAnimals = 0;
                    for (Location location : island.getAllLocations()) {
                        totalAnimals += location.getAnimals().size();
                    }

                    if (totalAnimals < SimulationConfig.MIN_ANIMALS_TO_CONTINUE) {
                        output.println("Все животные вымерли! Симуляция завершена.");
                        running.set(false);
                        break;
                    }

                    tickBarrier.await();

                    Thread.sleep(SimulationConfig.TICK_DURATION_MS);
                    currentDay++;

                    tickBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                    running.set(false);
                }
            }

            stop();

            output.println("Симуляция завершена после " + currentDay + " дней.");
        }
    }

    public void stop() {
        if (running.get()) {
            output.println("\n=== СИМУЛЯЦИЯ ЗАВЕРШЕНА ===");
            output.println("Всего прошло дней: " + currentDay);

            running.set(false);

            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(SHUTDOWN_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }

            animalLifecycle.shutdown();
            plantLifecycle.shutdown();
        }
    }
}