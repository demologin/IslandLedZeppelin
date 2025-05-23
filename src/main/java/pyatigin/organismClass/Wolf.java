package pyatigin.organismClass;

import pyatigin.abstractClass.Animal;
import pyatigin.annotation.Life;
import pyatigin.annotation.OrganismProperties;

@Life
@OrganismProperties(weight = 50, maxCountLocation = 30,needFoodKg = 8,speed = 3)
public class Wolf extends Animal {
    public Wolf(String name) {
        super(name);
    }
}
