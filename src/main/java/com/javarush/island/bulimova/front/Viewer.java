package com.javarush.island.bulimova.front;

import com.javarush.island.bulimova.entity.organisms.Organism;
import com.javarush.island.bulimova.entity.organisms.animals.herbivores.*;
import com.javarush.island.bulimova.entity.organisms.animals.predators.*;
import com.javarush.island.bulimova.entity.organisms.plants.Grass;
import com.javarush.island.bulimova.map.Cell;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
                Map<String, Integer> countMap = new HashMap<>();

                for (Organism organism : organisms) {
                    if (organism instanceof Wolf) {

                        countWolf++;
                        totalCount++;
                    }
                    if (organism instanceof Boa) {
                        countBoa++;
                        totalCount++;
                    }
                    if (organism instanceof Fox) {
                        countFox++;
                        totalCount++;
                    }
                    if (organism instanceof Bear) {
                        countBear++;
                        totalCount++;
                    }
                    if (organism instanceof Eagle) {
                        countEagle++;
                        totalCount++;
                    }
                    if (organism instanceof Horse) {
                        countHorse++;
                        totalCount++;
                    }
                    if (organism instanceof Deer) {
                        countDeer++;
                        totalCount++;
                    }
                    if (organism instanceof Rabbit) {
                        countRabbit++;
                        totalCount++;
                    }
                    if (organism instanceof Mouse) {
                        countMouse++;
                        totalCount++;
                    }
                    if (organism instanceof Goat) {
                        countGoat++;
                        totalCount++;
                    }
                    if (organism instanceof Sheep) {
                        countSheep++;
                        totalCount++;
                    }
                    if (organism instanceof Boar) {
                        countBoar++;
                        totalCount++;
                    }
                    if (organism instanceof Buffalo) {
                        countBuffalo++;
                        totalCount++;
                    }
                    if (organism instanceof Duck) {
                        countDuck++;
                        totalCount++;
                    }
                    if (organism instanceof Grass) {
                        countGrass++;
                    }
                    String emoji = getEmojiForOrganism(organism);
                    if (emoji == null) continue;
                    countMap.put(emoji, countMap.getOrDefault(emoji, 0) + 1);
                    totalCount++;
                }
                String maxEmoji = "";
                int maxCount = 0;

                for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                    if (entry.getValue() > maxCount) {
                        maxCount = entry.getValue();
                        maxEmoji = entry.getKey();
                    }
                }

                System.out.println("Maximum animal in a cell [" + i + "," + j + "]: " + maxEmoji + " × " + maxCount);
                maxEmoji="";
                maxCount=0;
            }

        }

        System.out.print("Total animals " + totalCount +" || ");
        System.out.print ("Total \uD83D\uDC3A "  + countWolf +" || ");
        System.out.print("Total \uD83D\uDC0D "  + countBoa +" || ");
        System.out.print("Total \uD83E\uDD8A "  + countFox +" || ");
        System.out.print("Total \uD83D\uDC3B "  + countBear +" || ");
        System.out.print("Total \uD83E\uDD85 "  + countEagle +" || ");
        System.out.print("Total \uD83D\uDC0E "  + countHorse +" || ");
        System.out.println("Total \uD83E\uDD8C "  + countDeer +" || ");
        System.out.print("Total \uD83D\uDC07 "  + countRabbit +" || ");
        System.out.print("Total \uD83D\uDC01 "  + countMouse +" || ");
        System.out.print("Total \uD83D\uDC10 "  + countGoat +" || ");
        System.out.print("Total \uD83D\uDC11 "  + countSheep +" || ");
        System.out.print("Total \uD83D\uDC17 "  + countBoar +" || ");
        System.out.print("Total \uD83D\uDC03 "  + countBuffalo +" || ");
        System.out.print("Total \uD83E\uDD86 "  + countDuck +" || ");
        System.out.println("Total \uD83C\uDF3F "  + countGrass +" || ");
        System.out.println();
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

    private String getEmojiForOrganism(Organism organism) {
        if (organism instanceof Wolf) return "🐺";
        if (organism instanceof Boa) return "🐍";
        if (organism instanceof Fox) return "🦊";
        if (organism instanceof Bear) return "🐻";
        if (organism instanceof Eagle) return "🦅";
        if (organism instanceof Horse) return "🐎";
        if (organism instanceof Deer) return "🦌";
        if (organism instanceof Rabbit) return "🐇";
        if (organism instanceof Mouse) return "🐁";
        if (organism instanceof Goat) return "🐐";
        if (organism instanceof Sheep) return "🐑";
        if (organism instanceof Boar) return "🐗";
        if (organism instanceof Buffalo) return "🐃";
        if (organism instanceof Duck) return "🦆";
        if (organism instanceof Grass) return "🌿";
        return null;
    }
}
