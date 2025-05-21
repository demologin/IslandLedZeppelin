package com.javarush.island.bulimova.util.constants.factory;

import com.javarush.island.bulimova.entity.organisms.Organisms;
import com.javarush.island.bulimova.entity.organisms.animals.predators.Boa;
import com.javarush.island.bulimova.entity.organisms.animals.predators.Fox;
import com.javarush.island.bulimova.entity.organisms.animals.predators.Wolf;
import com.javarush.island.bulimova.map.Cell;
import com.javarush.island.bulimova.util.constants.Probability;


import java.util.concurrent.ThreadLocalRandom;


public class FactoryOrganism {


    void map(Cell[][] map) {
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


                    }
                }
            }
        }
    }
}