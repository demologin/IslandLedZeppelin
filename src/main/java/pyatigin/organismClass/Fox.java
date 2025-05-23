package pyatigin.organismClass;

import pyatigin.abstractClass.Animal;
import pyatigin.annotation.Life;
import pyatigin.annotation.OrganismProperties;

@Life
@OrganismProperties(weight = 8, maxCountLocation = 30,needFoodKg = 2,speed = 2)
public class Fox extends Animal {
    public Fox(String name) {
        super(name);
    }
}
