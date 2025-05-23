package pyatigin.organismClass;

import pyatigin.abstractClass.Grass;
import pyatigin.annotation.Life;
import pyatigin.annotation.OrganismProperties;
@Life
@OrganismProperties(weight = 1, maxCountLocation = 200,needFoodKg = 0)
public class GreenGrass extends Grass {
    public GreenGrass(String name) {
        super(name);
    }
}
