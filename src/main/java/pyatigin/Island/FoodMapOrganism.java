package pyatigin.Island;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class FoodMapOrganism {
    private final Map<String, Integer> foodItems;

    public FoodMapOrganism() {
        this.foodItems = new HashMap<>();
    }

    public void addFoodItem(String foodName, Integer quantity) {
        foodItems.put(foodName, quantity);
    }
    public int getFoodItem(String foodName) {
        return foodItems.get(foodName);
    }

    @Override
    public String toString() {
        return "FoodMapOrganism{" +
                "foodItems=" + foodItems +
                '}';
    }
}
