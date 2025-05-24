package com.javarush.island.lepeshinskij.visualization;

import com.javarush.island.lepeshinskij.model.core.Island;
import com.javarush.island.lepeshinskij.model.core.Location;
import com.javarush.island.lepeshinskij.model.organisms.Animal;
import com.javarush.island.lepeshinskij.model.organisms.Plant;
import com.javarush.island.lepeshinskij.model.organisms.predators.*;
import com.javarush.island.lepeshinskij.model.organisms.herbivores.*;
import com.javarush.island.lepeshinskij.service.OutputFactory;
import com.javarush.island.lepeshinskij.service.OutputService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IslandMapRenderer {
    private static final OutputService output = OutputFactory.getOutputService();

    private static final Map<Class<?>, Character> ENTITY_SYMBOLS = new HashMap<>();

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    static {
        ENTITY_SYMBOLS.put(Wolf.class, 'W');
        ENTITY_SYMBOLS.put(Bear.class, 'B');
        ENTITY_SYMBOLS.put(Fox.class, 'F');
        ENTITY_SYMBOLS.put(Eagle.class, 'E');
        ENTITY_SYMBOLS.put(Snake.class, 'S');

        ENTITY_SYMBOLS.put(Horse.class, 'H');
        ENTITY_SYMBOLS.put(Deer.class, 'D');
        ENTITY_SYMBOLS.put(Rabbit.class, 'R');
        ENTITY_SYMBOLS.put(Mouse.class, 'M');
        ENTITY_SYMBOLS.put(Goat.class, 'G');
        ENTITY_SYMBOLS.put(Sheep.class, 'S');
        ENTITY_SYMBOLS.put(Boar.class, 'O');
        ENTITY_SYMBOLS.put(Buffalo.class, 'U');
        ENTITY_SYMBOLS.put(Duck.class, 'K');
        ENTITY_SYMBOLS.put(Caterpillar.class, 'C');

        ENTITY_SYMBOLS.put(Plant.class, '*');
    }

    public static void renderMap(Island island) {
        output.println("\n=== КАРТА ОСТРОВА ===");

        printHorizontalBorder(island.getWidth());

        for (int y = 0; y < island.getHeight(); y++) {
            output.print("│ ");
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);
                renderLocation(location);
                output.print(" ");
            }
            output.println("│");
        }

        printHorizontalBorder(island.getWidth());

        printLegend();
    }

    private static void renderLocation(Location location) {
        List<Animal> animals = location.getAnimals();

        if (!animals.isEmpty()) {
            Animal animal = animals.get(0);
            Character symbol = ENTITY_SYMBOLS.getOrDefault(animal.getClass(), '?');

            String color = animal instanceof Predator ? RED : YELLOW;
            output.print(color + symbol + RESET);
        } else if (location.getPlantsCount() > 0) {
            output.print(GREEN + "*" + RESET);
        } else {
            output.print(BLUE + "·" + RESET);
        }
    }

    private static void printHorizontalBorder(int width) {
        output.print("┌");
        for (int i = 0; i < width * 2 - 1; i++) {
            output.print("─");
        }
        output.println("┐");
    }

    private static void printLegend() {
        output.println("\n=== ЛЕГЕНДА ===");
        output.println(RED + "W" + RESET + " - Волк, " +
                RED + "B" + RESET + " - Медведь, " +
                RED + "F" + RESET + " - Лиса, " +
                RED + "E" + RESET + " - Орёл, " +
                RED + "S" + RESET + " - Змея");

        output.println(YELLOW + "H" + RESET + " - Лошадь, " +
                YELLOW + "D" + RESET + " - Олень, " +
                YELLOW + "R" + RESET + " - Кролик, " +
                YELLOW + "M" + RESET + " - Мышь, " +
                YELLOW + "G" + RESET + " - Коза");

        output.println(YELLOW + "S" + RESET + " - Овца, " +
                YELLOW + "O" + RESET + " - Кабан, " +
                YELLOW + "U" + RESET + " - Буйвол, " +
                YELLOW + "K" + RESET + " - Утка, " +
                YELLOW + "C" + RESET + " - Гусеница");

        output.println(GREEN + "*" + RESET + " - Растения, " +
                BLUE + "·" + RESET + " - Пустая клетка");
    }
}