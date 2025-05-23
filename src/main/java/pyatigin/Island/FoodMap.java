package pyatigin.Island;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import pyatigin.abstractClass.Organism;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class FoodMap {
    @Getter
    private Map<String, FoodMapOrganism> foodMap;
    private final String yamlFilePath = "src/main/resources/pyatigin/foodMap.yaml";
    private final List<Organism> organismsIsland;

    public FoodMap(List<Organism>organismsIsland) {
        if (!loadFoodMap()) {
            FoodMapDefault foodMapDefault = new FoodMapDefault();
            foodMap = foodMapDefault.getFoodMap();
            saveFoodMap(foodMap);
        }
        this.organismsIsland = organismsIsland;
        checkAndAddOrganismClasses();
        loadFoodMap();
    }

    private void saveFoodMap(Map<String, FoodMapOrganism> foodMap) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File(yamlFilePath), foodMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadFoodMap() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            foodMap = mapper.readValue(new File(yamlFilePath), mapper.getTypeFactory().constructMapType(Map.class, String.class, FoodMapOrganism.class));
            return true; // Успешно загружено
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Ошибка при загрузке
        }
    }

    public FoodMapOrganism getFoodMap(String nameOrganism) {
        return foodMap.get(nameOrganism);
    }

    private void checkAndAddOrganismClasses() {
        for (Organism organism : organismsIsland) {
            String organismName = organism.getName();
            if (!foodMap.containsKey(organismName)) {
                FoodMapOrganism newOrganism = new FoodMapOrganism();

                foodMap.put(organismName, newOrganism);
                saveFoodMap(foodMap);
                System.out.println("Добавлен новый класс: " + organismName);
                waitForUserInput();
            }
        }
    }

    private void waitForUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Внесите изменения в Yaml файл");
        System.out.print("Введите 'Y' для продолжения: ");
        while (true) {
            String input = scanner.nextLine();
            if ("Y".equalsIgnoreCase(input)) {
                break;
            } else {
                System.out.print("Неверный ввод. Пожалуйста, введите 'Y': ");
            }
        }
    }
}