package com.javarush.island.lepeshinskij.service;

public class OutputFactory {
    private static OutputService outputService;

    private OutputFactory() {
    }

    public static synchronized OutputService getOutputService() {
        if (outputService == null) {
            outputService = ConsoleOutputService.getInstance();
        }
        return outputService;
    }

    public static void setOutputService(OutputService service) {
        outputService = service;
    }
}