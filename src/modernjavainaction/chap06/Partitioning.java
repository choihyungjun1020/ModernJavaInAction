package modernjavainaction.chap06;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static modernjavainaction.chap06.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * 분할 연산
 * Predicate 를 분류 함수 (Boolean 을 반환하는 함수)로 사용하는 특수 그룹화 기능
 */
public class Partitioning {
    public static void main(String[] args) {
        System.out.println("partitionedMenu = " + partitionedMenu());
        System.out.println("vegetarianMenus Use Key = " + partitionedMenu().get(true));
        System.out.println("vegetarianMenus Use Filter = " + vegetarianMenus());

        System.out.println("Dish partitioned by vegetarian = " + partitionByVegetarian());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
    }

    private static Map<Boolean, List<Dish>> partitionedMenu() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static List<Dish> vegetarianMenus() {
        return menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
    }

    private static Map<Boolean, Map<Type, List<Dish>>> partitionByVegetarian() {
        return menu.stream()
                .collect(
                        partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))
                );
    }

    private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }
}
