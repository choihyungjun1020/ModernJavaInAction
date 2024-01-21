package modernjavainaction.chap04;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

public class StreamBasic {
    public static void main(String[] args) {
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        System.out.println(threeHighCaloricDishNames);
    }
}
