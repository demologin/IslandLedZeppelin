package pyatigin.organismClass;

import pyatigin.abstractClass.Animal;
import pyatigin.annotation.Life;
import pyatigin.annotation.OrganismProperties;

@Life
@OrganismProperties(weight = 2, maxCountLocation = 150,needFoodKg = 1,speed = 2)
public class Rabbit extends Animal {
    public Rabbit(String name) {
        super(name);
    }
}
