package pyatigin.simulationLife;

import pyatigin.Island.Island;
import pyatigin.interfaceClass.ViewIsland;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationLife {
    private final Island island;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final ViewIsland viewIsland;

    public SimulationLife(Island island, ViewIsland viewIsland) {
        this.viewIsland = viewIsland;
        this.island = island;
    }

    public void startSimulation() {
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Обновление острова");
            viewIsland.showIsland(island);
            Lifecycle lifecycle = new Lifecycle(island);
            lifecycle.start();
        }, 0, 1, TimeUnit.SECONDS);

    }
}
