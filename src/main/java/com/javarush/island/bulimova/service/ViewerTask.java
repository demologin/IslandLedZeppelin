package com.javarush.island.bulimova.service;

import com.javarush.island.bulimova.front.Viewer;
import com.javarush.island.bulimova.map.Cell;

public class ViewerTask implements Runnable {
    private final Viewer viewer;
    private final Cell[][] map;

    public ViewerTask(Viewer viewer, Cell[][] map) {
        this.viewer = viewer;
        this.map = map;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                viewer.scan(map);
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

