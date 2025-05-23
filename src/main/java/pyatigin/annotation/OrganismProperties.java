package pyatigin.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface OrganismProperties {
    int weight();
    int maxCountLocation();
    double needFoodKg() default 0.0;
    int speed() default 0;
}
