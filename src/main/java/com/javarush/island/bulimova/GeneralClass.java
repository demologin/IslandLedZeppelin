package com.javarush.island.bulimova;

import com.javarush.island.bulimova.entity.organisms.Organism;
import com.javarush.island.bulimova.entity.organisms.animals.herbivores.Mouse;
import com.javarush.island.bulimova.entity.organisms.animals.predators.Wolf;
import com.javarush.island.bulimova.entity.organisms.plants.Grass;
import com.javarush.island.bulimova.front.Viewer;
import com.javarush.island.bulimova.map.Cell;
import com.javarush.island.bulimova.map.Map;
import com.javarush.island.bulimova.util.constants.Constants;
import com.javarush.island.bulimova.util.constants.factory.FactoryOrganism;


public class GeneralClass {

    public static void main(String[] args) {
        FactoryOrganism factoryOrganism = new FactoryOrganism();

        Map map = new Map(Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
        Cell[][] initMap = factoryOrganism.init(map.getMap());
        map.setMap(initMap);
        Viewer viewer = new Viewer();
        viewer.scan(map.getMap());
//        for (int i = 0; i < map.getWidth(); i++) {
//            for (int j = 0; j < map.getHeight(); j++) {
//                Cell cell = map.getMap()[i][j];
//                for (int i1 = 0; i1 < cell.getOrganism().size(); i1++) {
//                    Organism organism = cell.getOrganism().get(i1);
//                    if (organism instanceof Mouse) {
//                       // ((Mouse) organism).eat(cell);
//                        ((Mouse) organism).move(cell, map);
//                    }
//
//                }
//            }
//        }
//
//        viewer.scan(map.getMap());
    }
}
