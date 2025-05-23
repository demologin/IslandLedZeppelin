package pyatigin.Island;

import lombok.Getter;
import pyatigin.organismClass.GreenGrass;
import pyatigin.organismClass.Rabbit;

import java.util.*;

public class FoodMapDefault {
    private final List<String> listClass;
    @Getter
    private final Map<String, FoodMapOrganism> foodMap; // Хранит карту еды
    private final int[][] probabilityEat;

    public FoodMapDefault() {
        this.listClass = Arrays.asList(Rabbit.class.getSimpleName(), GreenGrass.class.getSimpleName());
        this.probabilityEat = new int[][]{{0, 100}, {0, 0}};
        this.foodMap = createFoodMapDefault();
    }

    public Map<String, FoodMapOrganism> createFoodMapDefault() {
        Map<String, FoodMapOrganism> foodMap = new HashMap<>();

        for (int i = 0; i < listClass.size(); i++) {
            String currentClass = listClass.get(i);
            FoodMapOrganism foodMapOrganism = new FoodMapOrganism();

            for (int j = 0; j < listClass.size(); j++) {
                foodMapOrganism.addFoodItem(listClass.get(j), probabilityEat[i][j]);
            }
            foodMap.put(currentClass, foodMapOrganism);
        }
        return foodMap;
    }

}
