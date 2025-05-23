package pyatigin;

import pyatigin.Island.Island;


import pyatigin.factory.OrganismFactory;
import pyatigin.interfaceClass.ViewIsland;

import pyatigin.simulationLife.SimulationLife;
import pyatigin.view.ConsoleView;

public class Main {
    public static void main(String[] args) {

        OrganismFactory organismFactory = new OrganismFactory();
        Island island = new Island();
        island.IslandCreate(organismFactory);
        ViewIsland viewIsland = new ConsoleView();
        viewIsland.showIsland(island);
       System.out.println("Началась жизнь");
        SimulationLife simulationLife = new SimulationLife(island, viewIsland);
        simulationLife.startSimulation();

    }
}
