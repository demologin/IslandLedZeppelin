package com.javarush.island.lepeshinskij.service;

public class ConsoleOutputService implements OutputService {
    private static ConsoleOutputService instance;

    private ConsoleOutputService() {
    }

    public static synchronized ConsoleOutputService getInstance() {
        if (instance == null) {
            instance = new ConsoleOutputService();
        }
        return instance;
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    @Override
    public void newLine() {
        System.out.println();
    }
}