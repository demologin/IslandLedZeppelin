package pyatigin.util;

import org.reflections.Reflections;
import pyatigin.abstractClass.Organism;
import pyatigin.annotation.Life;

import java.util.Set;
import java.util.stream.Collectors;

public class GetFromAnnotationLife {
    private static final String packageName = "pyatigin";
    private static final Reflections reflections = new Reflections(packageName);

    public  static Set<Class<? extends Organism>> getClasses(Class<? extends Life> annotation ) {
        Set<Class<?>> organismClasses = reflections.getTypesAnnotatedWith(annotation);
        Set<Class<? extends Organism>> filteredClasses = organismClasses.stream()
                .filter(Organism.class::isAssignableFrom)
                //TODO как избавиться от ошибки
                .map(clazz -> (Class<? extends Organism>) clazz)
                .collect(Collectors.toSet());
        return filteredClasses;
    }
}
