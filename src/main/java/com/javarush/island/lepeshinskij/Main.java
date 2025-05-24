package com.javarush.island.lepeshinskij;

import com.javarush.island.lepeshinskij.service.InputFactory;
import com.javarush.island.lepeshinskij.service.InputService;
import com.javarush.island.lepeshinskij.service.OutputFactory;
import com.javarush.island.lepeshinskij.service.OutputService;
import com.javarush.island.lepeshinskij.simulation.IslandSimulation;
public class Main {
    private static final OutputService output = OutputFactory.getOutputService();
    private static final InputService input = InputFactory.getInputService();
    private static final int DEFAULT_DISPLAY_MODE = 2;

    public static void main(String[] args) {
        output.println("Запуск симуляции острова");
        output.println("Выберите режим отображения:");
        output.println("1 - Только статистика");
        output.println("2 - Статистика и карта острова");

        int displayMode = input.readInt(DEFAULT_DISPLAY_MODE);
        IslandSimulation simulation = new IslandSimulation();
        simulation.setDisplayMode(displayMode);
        simulation.start();
        input.close();
    }
}