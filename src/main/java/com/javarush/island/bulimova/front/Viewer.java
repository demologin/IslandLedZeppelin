package com.javarush.island.bulimova.front;

import com.javarush.island.bulimova.entity.organisms.Organism;
import com.javarush.island.bulimova.entity.organisms.animals.herbivores.*;
import com.javarush.island.bulimova.entity.organisms.animals.predators.*;
import com.javarush.island.bulimova.entity.organisms.plants.Grass;
import com.javarush.island.bulimova.map.Cell;

import java.util.List;


public class Viewer {


    public void scan(Cell[][] map) {

        int totalCount = 0;
        int countWolf = 0;
        int countBoa = 0;
        int countFox = 0;
        int countBear = 0;
        int countEagle = 0;
        int countHorse = 0;
        int countDeer = 0;
        int countRabbit = 0;
        int countMouse = 0;
        int countGoat = 0;
        int countSheep = 0;
        int countBoar = 0;
        int countBuffalo = 0;
        int countDuck = 0;
        int countGrass = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Cell cell = map[i][j];
                List<Organism> organisms = cell.getOrganism();

                for (Organism organism : organisms) {
                    if (!(organism instanceof Grass)) {
                        totalCount++;
                    }

                    if (organism instanceof Wolf) countWolf++;
                    else if (organism instanceof Boa) countBoa++;
                    else if (organism instanceof Fox) countFox++;
                    else if (organism instanceof Bear) countBear++;
                    else if (organism instanceof Eagle) countEagle++;
                    else if (organism instanceof Horse) countHorse++;
                    else if (organism instanceof Deer) countDeer++;
                    else if (organism instanceof Rabbit) countRabbit++;
                    else if (organism instanceof Mouse) countMouse++;
                    else if (organism instanceof Goat) countGoat++;
                    else if (organism instanceof Sheep) countSheep++;
                    else if (organism instanceof Boar) countBoar++;
                    else if (organism instanceof Buffalo) countBuffalo++;
                    else if (organism instanceof Duck) countDuck++;
                    else if (organism instanceof Grass) countGrass++;


                }
            }

        }
        System.out.println("""
                \n--- SUMMARY ---
                Total animals: %d
                🐺 %d || 🐍 %d || 🦊 %d || 🐻 %d || 🦅 %d || 🐎 %d
                🦌 %d || 🐇 %d || 🐁 %d || 🐐 %d || 🐑 %d || 🐗 %d
                🐃 %d || 🦆 %d || 🌿 %d
                """.formatted(totalCount, countWolf, countBoa, countFox, countBear, countEagle,
                countHorse, countDeer, countRabbit, countMouse, countGoat, countSheep,
                countBoar, countBuffalo, countDuck, countGrass
        ));
        totalCount = 0;
        countWolf = 0;
        countBoa = 0;
        countFox = 0;
        countBear = 0;
        countEagle = 0;
        countHorse = 0;
        countDeer = 0;
        countRabbit = 0;
        countMouse = 0;
        countGoat = 0;
        countSheep = 0;
        countBoar = 0;
        countBuffalo = 0;
        countDuck = 0;
        countGrass = 0;
    }
}



