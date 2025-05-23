package com.javarush.island.bulimova.entity;

import com.javarush.island.bulimova.map.Cell;
import com.javarush.island.bulimova.map.Map;

public interface Move {
    void move(Cell currentCell, Map map);
}
