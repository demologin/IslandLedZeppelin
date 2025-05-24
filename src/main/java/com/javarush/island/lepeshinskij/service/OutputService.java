package com.javarush.island.lepeshinskij.service;

public interface OutputService {
    void println(String message);

    void print(String message);

    void printf(String format, Object... args);

    void newLine();
}