package com.javarush.island.lepeshinskij.service;

public class InputFactory {
    private static InputService inputService;

    public static InputService getInputService() {
        if (inputService == null) {
            inputService = new ConsoleInputService(OutputFactory.getOutputService());
        }
        return inputService;
    }
    
    public static void setInputService(InputService customInputService) {
        inputService = customInputService;
    }
}