package com.javarush.island.lepeshinskij.service;

public interface InputService {
    String readLine();

    int readInt(int defaultValue);

    void close();
}