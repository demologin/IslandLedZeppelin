package com.javarush.island.bulimova.map;

import com.javarush.island.bulimova.entity.organisms.Organisms;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

@Data
@NoArgsConstructor
public class Cell {

    private final ReentrantLock lock = new ReentrantLock();
    ArrayList<Organisms> organism = new ArrayList<>();


}
