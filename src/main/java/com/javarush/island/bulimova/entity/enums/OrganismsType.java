package com.javarush.island.bulimova.entity.enums;

public enum OrganismsType {
    WOLF("Wolf"),
    BOA("Boa"),
    FOX("Fox"),
    BEAR("Bear"),
    EAGLE("Eagle"),
    HORSE("Horse"),
    DEER("Deer"),
    RABBIT("Rabbit"),
    MOUSE("Mouse"),
    GOAT("Goat"),
    SHEEP("Sheep"),
    BOAR("Boar"),
    BUFFALO("Buffalo"),
    DUCK("Duck"),
    GRASS("Grass");

    private final String name;

    OrganismsType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
