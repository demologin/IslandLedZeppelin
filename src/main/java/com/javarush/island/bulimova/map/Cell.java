package com.javarush.island.bulimova.map;

import com.javarush.island.bulimova.entity.organisms.Organism;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

@Data
@NoArgsConstructor
public class Cell {

    public Cell(int x, int y) {
        this.y = y;
        this.x = x;
    }

    private int x;
    private int y;

    private final ReentrantLock lock = new ReentrantLock();
    ArrayList<Organism> organism = new ArrayList<>();


}
