package com.javarush.island.bulimova.map;

import com.javarush.island.bulimova.util.constants.Constants;
import lombok.Data;



@Data
public class Map {

   private Cell [][] map;
    int height;
    int width;

    public Map(int height, int  width) {
        this.map = new Cell[height][width];
    }



}
