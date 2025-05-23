package pyatigin.view;

import pyatigin.Island.Island;
import pyatigin.Island.Location;
import pyatigin.interfaceClass.ViewIsland;

import java.util.HashMap;
import java.util.Map;

public class ConsoleView implements ViewIsland {

    private Map<Class<?>, Double> previousMapIsland = new HashMap<>();

    public ConsoleView() {
    }

    @Override
    public void showIsland(Island island) {
        System.out.println("Статистика: ");
        getObjectForIsland(island);
    }

    private void getObjectForIsland(Island island) {
        Map<Class<?>, Double> currentMapIsland = new HashMap<>();
        Location[][] locationsIsland = island.getLocationsIsland();
        for (Location[] locations : locationsIsland) {
            for (Location location : locations) {
                location.getOrganismsForLocation().forEach(organism -> {
                    double countOrganismForLocation = organism.getWightOrganismForLocation() / organism.getWightOrganism();
                    currentMapIsland.merge(organism.getClass(), countOrganismForLocation, Double::sum);
//                    if (location.getX() < 4 && location.getY() < 4) {
//                        System.out.println(location.toString());
//                    }
                });
            }
        }
        System.out.println();
        displayResults(currentMapIsland);
        previousMapIsland = currentMapIsland; // Обновляем предыдущее состояние
    }

    private void displayResults(Map<Class<?>, Double> currentMapIsland) {
        currentMapIsland.forEach((organism, currentWeight) -> {
            Double previousWeight = previousMapIsland.get(organism);
            String color = "\u001B[0m"; // Сброс цвета (по умолчанию)

            if (previousWeight != null) {
                if (currentWeight > previousWeight) {
                    color = "\u001B[32m"; // Зеленый
                } else if (currentWeight < previousWeight) {
                    color = "\u001B[31m"; // Красный
                }
            }

            System.out.print(color + "Итог: " + organism.getSimpleName() + " " + currentWeight + "\u001B[0m "); // Сброс цвета
        });
        System.out.println();
    }
}
