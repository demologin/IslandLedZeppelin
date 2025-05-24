package com.javarush.island.lepeshinskij.simulation.lifecycle;

import com.javarush.island.lepeshinskij.model.core.Island;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.config.SimulationConfig;
import com.javarush.island.lepeshinskij.service.OutputFactory;
import com.javarush.island.lepeshinskij.service.OutputService;
import com.javarush.island.lepeshinskij.utils.RandomGenerator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PlantLifecycle implements Runnable {
    private final Island island;
    private final AtomicBoolean running;
    private final ExecutorService executorService;
    private final OutputService outputService;
    private boolean isShutdown = false;

    private static final int TASK_TIMEOUT_SECONDS = 5;
    private static final int MIN_THREADS = 2;
    private static final double PROCESSOR_UTILIZATION_FACTOR = 0.5;

    public PlantLifecycle(Island island, AtomicBoolean running) {
        this.island = island;
        this.running = running;
        this.outputService = OutputFactory.getOutputService();
        int processors = Runtime.getRuntime().availableProcessors();
        int numThreads = (int) (processors * PROCESSOR_UTILIZATION_FACTOR);
        this.executorService = Executors.newFixedThreadPool(Math.max(MIN_THREADS, numThreads));
    }

    @Override
    public void run() {
        if (isShutdown || !running.get()) {
            return;
        }

        island.getAllLocations().forEach(location -> {
            executorService.submit(() -> {
                try {
                    synchronized (location) {
                        growPlants(location);
                    }
                } catch (Exception e) {
                    outputService.println("Ошибка при обработке роста растений: " + e.getMessage());
                }
            });
        });
    }

    private void growPlants(Location location) {
        double currentWeight = location.getPlantsWeight();
        double maxWeight = location.getMaxPlantsWeight();

        if (currentWeight < maxWeight) {
            double growth = RandomGenerator.nextDouble() * SimulationConfig.PLANT_GROWTH_RATE * maxWeight;

            double newWeight = Math.min(currentWeight + growth, maxWeight);

            location.setPlantsWeight(newWeight);
        }
    }

    public void shutdown() {
        if (!isShutdown) {
            executorService.shutdownNow();
            try {
                if (!executorService.awaitTermination(TASK_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                    outputService.println("Не все задачи роста растений завершились при остановке пула");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            isShutdown = true;
        }
    }
}