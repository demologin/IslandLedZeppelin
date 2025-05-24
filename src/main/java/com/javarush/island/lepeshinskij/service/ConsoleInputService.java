package com.javarush.island.lepeshinskij.service;

import java.util.Scanner;

public class ConsoleInputService implements InputService {
    private final Scanner scanner;
    private final OutputService output;

    public ConsoleInputService(OutputService output) {
        this.scanner = new Scanner(System.in);
        this.output = output;
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public int readInt(int defaultValue) {
        try {
            String input = readLine();
            return Integer.parseInt(input);
        } catch (Exception e) {
            output.println("Неверный ввод. Будет использовано значение по умолчанию: " + defaultValue);
            return defaultValue;
        }
    }

    @Override
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}