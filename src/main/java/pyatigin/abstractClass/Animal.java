package pyatigin.abstractClass;

import lombok.Getter;
import pyatigin.Island.Location;
import pyatigin.annotation.OrganismProperties;
import pyatigin.interfaceClass.Eating;
import pyatigin.interfaceClass.Grow;
import pyatigin.interfaceClass.Moving;


@Getter
public abstract class Animal extends Organism implements Grow, Eating, Moving {
    private final double needFoodKg;
    private final int speed;
    public Animal(String name) {
        super(name);
        this.needFoodKg=getOrganismProperty(this.getClass()).needFoodKg();
        this.speed=getOrganismProperty(this.getClass()).speed();
    }

    @Override
    public String getName() {
        return super.getName();
    }
    public int getCountAnimals() {
       return (int) this.getWightOrganismForLocation()/this.getWightOrganism();
    }


    @Override
    public boolean move(Location locationNext, int count) {
       if (!locationNext.searchOrganism(this.getName())){
           locationNext.addOrganism(this);
           return true;
       }
        else {
           Animal animal = (Animal) locationNext.getOrganism(this.getName());
           int countAnimals = animal.getCountAnimals();
           int maxCountLocation = animal.getMAX_COUNT_LOCATION();
           if (countAnimals+count < maxCountLocation) {
               animal.setWightOrganismForLocation(animal.getWightOrganismForLocation()+this.getWightOrganism()*count);
               return true;
           }
       }
        return false;
    }
    public void updateWightOrganismForLocation(int count) {
        this.setWightOrganismForLocation(this.getWightOrganismForLocation()-this.getWightOrganism()*count);
    }
    @Override
    public double eat(Organism organism, Double wightNeedFood) {
        if (organism.getWightOrganismForLocation()-wightNeedFood>=0) {
            organism.setWightOrganismForLocation(organism.getWightOrganismForLocation()-wightNeedFood);
            return 0.0;
        }else {
            organism.setWightOrganismForLocation(0.0);
            return Math.abs(organism.getWightOrganismForLocation()-wightNeedFood);
        }
    }

    public void die(Double wightDieAnimal) {
        this.setWightOrganismForLocation(
                Math.max(0, this.getWightOrganismForLocation() - wightDieAnimal)
        );
    }

    private static OrganismProperties getOrganismProperty(Class<?> clazz) {
        OrganismProperties properties = clazz.getAnnotation(OrganismProperties.class);
        if (properties == null) {
            throw new IllegalStateException("No OrganismProperties annotation present on class: " + clazz.getName());
        }
        return properties;

    }
}
