package pyatigin.factory;

import pyatigin.abstractClass.Organism;
import pyatigin.annotation.Life;
import pyatigin.util.GetFromAnnotationLife;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class OrganismFactory {
    public <T extends Organism> T createLife(Class<T> clazz) {
        try {
          return   clazz.getDeclaredConstructor(String.class).newInstance(clazz.getSimpleName());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Organism> createRandomLifeFromAllAnnotatedClasses() {
        Set<Class<? extends Organism>> filteredClasses = GetFromAnnotationLife.getClasses(Life.class);
        List<Organism> organismsList = new ArrayList<>();
        for (Class<? extends Organism> organismClass : filteredClasses) {
            organismsList.add(createLife(organismClass));
        }
        return organismsList;
    }
}


