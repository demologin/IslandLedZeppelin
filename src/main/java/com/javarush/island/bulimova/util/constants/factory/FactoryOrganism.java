package com.javarush.island.bulimova.util.constants.factory;


import com.javarush.island.bulimova.entity.organisms.animals.herbivores.*;
import com.javarush.island.bulimova.entity.organisms.animals.predators.*;
import com.javarush.island.bulimova.entity.organisms.plants.Grass;
import com.javarush.island.bulimova.map.Cell;
import com.javarush.island.bulimova.util.constants.Probability;


import java.util.concurrent.ThreadLocalRandom;


public class FactoryOrganism {

   public Cell[][] init(Cell[][] map) {
        for (int i = 0; i < map.length; i++) {

            for (int j = 0; j < map[i].length; j++) {
                Cell cell = new Cell(i, j);

                for (String namesAnimal : Probability.NAMES_ANIMALS) {

                    switch (namesAnimal) {
                        case "Wolf": {
                            Wolf originWolf = new Wolf();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originWolf.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Wolf());
                            }

                        }
                        break;

                        case "Boa": {
                            Boa originBoa = new Boa();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originBoa.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Boa());

                            }
                        }
                        break;

                        case "Fox": {
                            Fox originFox = new Fox();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originFox.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Fox());

                            }
                        }
                        break;

                        case "Bear": {
                            Bear originBear = new Bear();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originBear.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Bear());

                            }
                        }
                        break;

                        case "Eagle": {
                            Eagle originEagle = new Eagle();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originEagle.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Eagle());

                            }
                        }
                        break;

                        case "Horse": {
                            Horse originHorse = new Horse();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originHorse.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Horse());

                            }
                        }
                        break;

                        case "Deer": {
                            Deer originDeer = new Deer();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originDeer.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Deer());

                            }
                        }
                        break;

                        case "Rabbit": {
                            Rabbit originRabbit = new Rabbit();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originRabbit.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Rabbit());

                            }
                        }
                        break;

                        case "Mouse": {
                            Mouse originMouse = new Mouse();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originMouse.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Mouse());

                            }
                        }
                        break;

                        case "Goat": {
                            Goat originGoat = new Goat();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originGoat.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Goat());

                            }
                        }
                        break;

                        case "Sheep": {
                            Sheep originSheep = new Sheep();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originSheep.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Sheep());

                            }
                        }
                        break;

                        case "Boar": {
                            Boar originBoar = new Boar();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originBoar.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Boar());

                            }
                        }
                        break;

                        case "Buffalo": {
                            Buffalo originBuffalo = new Buffalo();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originBuffalo.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Buffalo());

                            }
                        }
                        break;

                        case "Duck": {
                            Duck originDuck = new Duck();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originDuck.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Duck());

                            }
                        }
                        break;

                        case "Grass": {
                            Grass originGrass = new Grass();
                            int current_Count = ThreadLocalRandom.current().nextInt(0, (originGrass.getCOUNT_IN_CELL() + 1));
                            for (int i1 = 0; i1 < current_Count; i1++) {
                                cell.getOrganism().add(new Grass());

                            }
                        }
                        break;


                    }
                }
                map[i][j] = cell;
            }
        }
        return map;
    }
}