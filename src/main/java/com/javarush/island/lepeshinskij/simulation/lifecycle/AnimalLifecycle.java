package com.javarush.island.lepeshinskij.simulation.lifecycle;

import com.javarush.island.lepeshinskij.config.SimulationConfig;
import com.javarush.island.lepeshinskij.model.enums.AnimalType;
import com.javarush.island.lepeshinskij.model.core.Island;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.model.organisms.predators.Predator;
import com.javarush.island.lepeshinskij.service.OutputFactory;
import com.javarush.island.lepeshinskij.service.OutputService;
import com.javarush.island.lepeshinskij.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalLifecycle implements Runnable {
    private final Island island;
    private final AtomicBoolean running;
    private final CyclicBarrier tickBarrier;
    private final ExecutorService executorService;
    private boolean isShutdown = false;
    private final OutputService outputService;

    private static final int TASK_TIMEOUT_SECONDS = 15;
    private static final long LOCK_TIMEOUT_MS = 100;

    public AnimalLifecycle(Island island, AtomicBoolean running, CyclicBarrier tickBarrier) {
        this.island = island;
        this.running = running;
        this.tickBarrier = tickBarrier;
        this.outputService = OutputFactory.getOutputService();

        int processors = Runtime.getRuntime().availableProcessors();
        long countOfActualAnimalTypes = java.util.Arrays.stream(AnimalType.values())
                .filter(type -> !type.name().equals("PLANT"))
                .count();
        int animalTypes = (int) countOfActualAnimalTypes;
        this.executorService = Executors.newFixedThreadPool(Math.min(animalTypes, processors));
    }

    @Override
    public void run() {
        if (isShutdown || !running.get()) {
            return;
        }

        try {
            List<Location> locations = island.getAllLocations();

            Map<AnimalType, List<Animal>> animalsByType = groupAnimalsByType(locations);

            List<CompletableFuture<Void>> futures = new ArrayList<>();

            for (Map.Entry<AnimalType, List<Animal>> entry : animalsByType.entrySet()) {
                AnimalType type = entry.getKey();
                List<Animal> animals = entry.getValue();

                if (animals.isEmpty()) {
                    continue;
                }

                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        outputService.println("Обработка животных типа: " + type + " (количество: " + animals.size() + ")");
                        for (Animal animal : animals) {
                            if (!animal.isDead()) {
                                processAnimal(animal);
                            }
                        }
                    } catch (Exception e) {
                        outputService.println("Ошибка при обработке животных типа " + type + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                }, executorService);
                futures.add(future);
            }

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                    futures.toArray(new CompletableFuture[0])
            );
            try {
                allFutures.get(TASK_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                outputService.println("Не все задачи жизненного цикла животных завершились вовремя: таймаут " +
                        TASK_TIMEOUT_SECONDS + " секунд");
                int unfinishedCount = 0;
                for (CompletableFuture<Void> future : futures) {
                    if (!future.isDone()) {
                        unfinishedCount++;
                    }
                }
                outputService.println("Количество незавершенных задач: " + unfinishedCount);
            } catch (Exception e) {
                outputService.println("Ошибка при ожидании завершения задач: " + e.getMessage());
                e.printStackTrace();
            }

            tickBarrier.await();

        } catch (Exception e) {
            outputService.println("Ошибка в жизненном цикле животных: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Map<AnimalType, List<Animal>> groupAnimalsByType(List<Location> locations) {
        Map<AnimalType, List<Animal>> animalsByType = new HashMap<>();

        for (AnimalType type : AnimalType.values()) {
            if (type != AnimalType.PLANT) {
                animalsByType.put(type, new ArrayList<>());
            }
        }

        for (Location location : locations) {
            List<Animal> animals = new ArrayList<>(location.getAnimals());
            for (Animal animal : animals) {
                if (!animal.isDead()) {
                    AnimalType type = animal.getAnimalType();
                    if (type != AnimalType.PLANT) {
                        animalsByType.get(type).add(animal);
                    }
                }
            }
        }

        return animalsByType;
    }

    private void processAnimal(Animal animal) {
        if (animal.isDead()) {
            return;
        }

        Location location = animal.getLocation();
        if (location == null) {
            return;
        }

        boolean lockAcquired = false;
        try {
            lockAcquired = location.getLock().tryLock(LOCK_TIMEOUT_MS, TimeUnit.MILLISECONDS);
            if (!lockAcquired) {
                return;
            }

            boolean diedFromHunger = animal.decreaseHungerLevel();
            if (diedFromHunger || animal.isDead()) {
                return;
            }

            animal.eat();

            if (animal instanceof Predator) {
                if (!animal.isHungry() && RandomGenerator.getProbability() <= SimulationConfig.PREDATOR_REPRODUCTION_PROBABILITY) {
                    animal.reproduce();
                }
            } else {
                if (!animal.isHungry() && RandomGenerator.getProbability() <= SimulationConfig.HERBIVORE_REPRODUCTION_PROBABILITY) {
                    animal.reproduce();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        } finally {
            if (lockAcquired) {
                location.getLock().unlock();
            }
        }

        Location target = animal.chooseDirectionToMove();

        if (target != null && target != location) {
            moveAnimal(animal, location, target);
        }
    }

    private void moveAnimal(Animal animal, Location source, Location target) {
        if (animal.isDead() || animal.getLocation() != source) {
            return;
        }

        Location first = source.compareTo(target) < 0 ? source : target;
        Location second = source.compareTo(target) < 0 ? target : source;

        boolean firstLockAcquired = false;
        boolean secondLockAcquired = false;

        try {
            firstLockAcquired = first.getLock().tryLock(LOCK_TIMEOUT_MS, TimeUnit.MILLISECONDS);
            if (!firstLockAcquired) {
                return;
            }

            secondLockAcquired = second.getLock().tryLock(LOCK_TIMEOUT_MS, TimeUnit.MILLISECONDS);
            if (!secondLockAcquired) {
                return;
            }

            if (!animal.isDead() && animal.getLocation() == source) {
                int currentCount = target.getAnimalCountByType(animal.getClass());
                if (currentCount < animal.getMaxCountPerLocation()) {
                    animal.move(target);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (secondLockAcquired) {
                second.getLock().unlock();
            }
            if (firstLockAcquired) {
                first.getLock().unlock();
            }
        }
    }

    public void shutdown() {
        if (!isShutdown) {
            executorService.shutdownNow();
            try {
                if (!executorService.awaitTermination(TASK_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                    outputService.println("Не все задачи жизненного цикла завершились при остановке пула");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            isShutdown = true;
        }
    }
}