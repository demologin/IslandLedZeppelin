package com.javarush.island.bulimova;

import com.javarush.island.bulimova.front.Viewer;
import com.javarush.island.bulimova.map.Cell;
import com.javarush.island.bulimova.map.Map;
import com.javarush.island.bulimova.service.ExecutorAnimals;
import com.javarush.island.bulimova.util.constants.Constants;
import com.javarush.island.bulimova.util.constants.factory.FactoryOrganism;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GeneralClass {

    public static void main(String[] args) {
        FactoryOrganism factoryOrganism = new FactoryOrganism();

        Map map = new Map(Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
        Cell[][] initMap = factoryOrganism.init(map.getMap());
        map.setMap(initMap);

        Cell[][] cells = map.getMap();
        ExecutorService executor = Executors.newFixedThreadPool(Constants.QUANTITY_THREADS);
        Viewer viewer = new Viewer();


        while (true) {
            ArrayList<Callable<Void>> executionTasks = new ArrayList<>();
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    executionTasks.add(new ExecutorAnimals(cells[i][j], map));
                }
            }

            try {
                executor.invokeAll(executionTasks);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            viewer.scan(map.getMap());

            try {
                Thread.sleep(1_000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

    }
}
