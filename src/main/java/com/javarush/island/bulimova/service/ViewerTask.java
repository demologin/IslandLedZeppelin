package com.javarush.island.bulimova.service;

import com.javarush.island.bulimova.front.Viewer;
import com.javarush.island.bulimova.map.Map;
import lombok.Data;

@Data
public class ViewerTask implements Runnable {
    private Viewer viewer;
    private volatile Map map;

    public ViewerTask(Viewer viewer, Map map) {
        this.viewer = viewer;
        this.map = map;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                viewer.scan(map.getMap());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

