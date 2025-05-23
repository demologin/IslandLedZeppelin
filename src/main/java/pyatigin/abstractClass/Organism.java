package pyatigin.abstractClass;

import lombok.Getter;
import lombok.Setter;
import pyatigin.annotation.OrganismProperties;
import pyatigin.interfaceClass.Grow;

@Getter
public abstract class Organism implements Grow {
    private final String name;
    private int MAX_COUNT_LOCATION;
    @Setter
    private double wightOrganismForLocation;
    private int wightOrganism;

    public Organism(String name) {
        this.name = name;
        this.MAX_COUNT_LOCATION = getOrganismProperty(this.getClass()).maxCountLocation();
        this.wightOrganism = getOrganismProperty(this.getClass()).weight();
    }

    @Override
    public void grow(Double x) {
        this.wightOrganismForLocation = x;
    }

    private static OrganismProperties getOrganismProperty(Class<?> clazz) {
        OrganismProperties properties = clazz.getAnnotation(OrganismProperties.class);
        if (properties == null) {
            throw new IllegalStateException("No OrganismProperties annotation present on class: " + clazz.getName());
        }
        return properties;

    }

}
