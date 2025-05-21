package com.javarush.island.bulimova.entity.organisms.animals.predators;

import com.javarush.island.bulimova.entity.enums.Gender;
import com.javarush.island.bulimova.entity.enums.OrganismsType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Wolf extends Predators {

    public Wolf() {
        super(40, "\uD83D\uDC3A", 50, 3);
    }


}









