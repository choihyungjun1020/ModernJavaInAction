package modernjavainaction.chap06;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static modernjavainaction.chap06.Dish.dishTags;
import static modernjavainaction.chap06.Dish.menu;

/**
 * groupingBy 연산은 '버킷' 개념으로 생각하면 쉽다. p.215
 */
public class Grouping {
    public static void main(String[] args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dishes grouped by type: " + groupDishesByCaloric());
        System.out.println("Caloric dishes grouped by type 1: " + groupCaloricDishesByType1());
        System.out.println("Caloric dishes grouped by type 2: " + groupCaloricDishesByType2());
        System.out.println("Dish names grouped by type: " + groupDishNamesByType());
        System.out.println("Dish tags grouped by type: " + groupDishTagsByType());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricByType());
        System.out.println("Most caloric dishes by type Use Optional: " + mostCaloricByTypeOptional());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

    private static Map<Type, List<Dish>> groupDishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloric() {
        return menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })
        );
    }

    private static Map<Type, List<Dish>> groupCaloricDishesByType1() {
        return menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));
    }

    private static Map<Type, List<Dish>> groupCaloricDishesByType2() {
        return menu.stream()
                .collect(groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500, toList())));
    }

    private static Map<Type, List<String>> groupDishNamesByType() {
        return menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));
    }

    private static Map<Type, Set<String>> groupDishTagsByType() {
        return menu.stream()
                .collect(groupingBy(Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
    }

    /**
     * 다수준 그룹화, {FISH={NORMAL=[salmon], DIET=[prawns]}, MEAT={NORMAL=[beef], FAT=[pork], DIET=[chicken]}, OTHER={NORMAL=[french fries, pizza], DIET=[rice, season fruit]}}
     */
    private static Map<Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }))
        );
    }

    private static Map<Type, Long> countDishesInGroups() {
        return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Type, Optional<Dish>> mostCaloricByType() {
        return menu.stream().collect(groupingBy(Dish::getType,
                maxBy(comparingInt(Dish::getCalories))));
    }

    private static Map<Type, Dish> mostCaloricByTypeOptional() {
        return menu.stream()
                .collect(
                        groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)
                        ));
    }

    private static Map<Type, Integer> sumCaloriesByType() {
        return menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));
    }

    private static Map<Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        },
                        toSet()
                ))
        );
    }

    /**
     * Use toCollection example
     */
    private static Map<Type, Set<CaloricLevel>> caloricLevelsByTypeUseToCollection() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping(dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return CaloricLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return CaloricLevel.NORMAL;
                                    } else {
                                        return CaloricLevel.FAT;
                                    }
                                },
                                toCollection(HashSet::new)
                        )));
    }
}
